package fr.convergence.proddoc.model.lib


import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory


class MaskMessageSerDes : Deserializer<MaskMessage>, Serializer<MaskMessage> {

    companion object {
        private val LOG = LoggerFactory.getLogger(MaskMessageSerDes::class.java)
    }

    override fun deserialize(topic: String?, data: ByteArray?): MaskMessage {
        requireNotNull(data) { " la chaine de caractères donnée pour la deserialisation est null" }
        require(data.size > 0) { " la chaine de caractères donnée pour la deserialisation est vide" }

        val donnees = String(data)
        LOG.debug("Données recues : $donnees")

        return Json.decodeFromString(donnees)
    }

    override fun serialize(topic: String?, data: MaskMessage?): ByteArray {
        requireNotNull(data) { " l'objet à déserialiser est null" }

        val donnees = Json.encodeToString(data)
        LOG.debug("Données générées : $donnees")

        return donnees.toByteArray()
    }

    override fun close() {
        LOG.debug("close")
    }

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        LOG.debug("configure")
    }
}