package fr.convergence.proddoc.model.lib.serdes


import fr.convergence.proddoc.model.lib.annotation.MaskTable
import fr.convergence.proddoc.model.lib.obj.PayloadDebezium
import io.debezium.serde.DebeziumSerdes
import org.apache.kafka.common.serialization.Deserializer
import org.reflections.Reflections
import org.slf4j.LoggerFactory.getLogger
import java.util.*


class MaskTableDeserialiseur : Deserializer<Any?> {

    companion object {
        private val LOG = getLogger(MaskTableDeserialiseur::class.java)

        private val deserializationPayloadProperties: HashMap<String, Any> =
            hashMapOf("unknown.properties.ignored" to "true")
        private val deserialisationClassProperties: HashMap<String, Any> =
            hashMapOf("unknown.properties.ignored" to "true", "from.field" to "after")
        private val classMaskTable =
            Reflections("fr.convergence.proddoc.model.table").getTypesAnnotatedWith(MaskTable::class.java, true)
    }

    override fun deserialize(topic: String, data: ByteArray): Any? {
        val tableName = getTableName(topic, data)
        tableName
            ?: throw IllegalArgumentException("Table name not found in debezium meta-data (payload->source->table)")
        LOG.debug("Table name in Meta-Data : $tableName")

        val classDeserialization =
            classMaskTable.filter { it.getAnnotation(MaskTable::class.java).value == tableName }.firstOrNull()
        classDeserialization
            ?: throw IllegalArgumentException("No class with annotation @MaskTable(\"$tableName\") found")
        LOG.debug("Class to deserialize into : $classDeserialization")

        return fillObjectWithJson(topic, data, classDeserialization)
    }

    private fun getTableName(topic: String, data: ByteArray): String? {
        val serde = DebeziumSerdes.payloadJson(PayloadDebezium::class.java)
        serde.configure(deserializationPayloadProperties, false)
        val payloadDebezium = serde.deserializer().deserialize(topic, data)
        return payloadDebezium.source?.table
    }

    private fun fillObjectWithJson(topic: String, data: ByteArray, classDeserialization: Class<*>): Any? {
        val serde = DebeziumSerdes.payloadJson(classDeserialization)
        serde.configure(deserialisationClassProperties, false)
        return serde.deserializer().deserialize(topic, data)
    }
}