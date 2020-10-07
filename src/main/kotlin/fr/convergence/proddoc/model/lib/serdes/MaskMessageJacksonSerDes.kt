package fr.convergence.proddoc.model.lib.serdes

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.node.ObjectNode
import fr.convergence.proddoc.model.lib.obj.MaskMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory.getLogger
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

@Provider
class MaskMessageJacksonSerDes : ContextResolver<ObjectMapper> {

    private val objectMapper: ObjectMapper

    override fun getContext(objectType: Class<*>?): ObjectMapper {
        return objectMapper
    }

    init {
        objectMapper = ObjectMapper()
        val module = SimpleModule("MaskMessageModuleJsonDeserializer")
        module.addDeserializer(MaskMessage::class.java, MaskMessageJsonDeserializer())
        objectMapper.registerModule(module)
    }
}

class MaskMessageJsonDeserializer : JsonDeserializer<MaskMessage>() {

    companion object {
        private val LOG = getLogger(MaskMessageJsonDeserializer::class.java)
    }

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): MaskMessage {

        val chaineContenenantMaskMessage = p!!.readValueAsTree<ObjectNode>().toString()
        LOG.debug("Message recu via Jackson : $chaineContenenantMaskMessage")
        val maskMessage: MaskMessage = Json.decodeFromString(chaineContenenantMaskMessage)
        LOG.debug("Décodé en : $maskMessage")

        return maskMessage
    }
}