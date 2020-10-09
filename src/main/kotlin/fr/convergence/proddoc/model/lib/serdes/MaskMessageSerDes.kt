package fr.convergence.proddoc.model.lib.serdes


import fr.convergence.proddoc.model.lib.obj.MaskMessage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory.getLogger


class MaskMessageSerDes : Deserializer<MaskMessage>, Serializer<MaskMessage> {

    companion object {
        private val LOG = getLogger(MaskMessageSerDes::class.java)
    }

    override fun deserialize(topic: String?, data: ByteArray?): MaskMessage {
        requireNotNull(data) { " la chaine de caractères donnée pour la deserialisation est null" }
        require(data.size > 0) { " la chaine de caractères donnée pour la déserialisation est vide" }

        val donnees = String(data)
        LOG.debug("Données recues : $donnees")

        return try {
            val decodeFromString = Json.decodeFromString<MaskMessage>(donnees)
            LOG.debug("Données recues décodées : $decodeFromString")
            decodeFromString
        } catch (ex: Exception) {
            LOG.error("Impossible de décoder les données recues ${ex.message} : $donnees", ex)
            MaskMessage.deserialisationKo(ex)
        }
    }

    override fun serialize(topic: String?, data: MaskMessage?): ByteArray {
        requireNotNull(data) { " l'objet à déserialiser est null" }

        val donnees = Json.encodeToString(data)
        LOG.debug("Données générées : $donnees")

        return donnees.toByteArray()
    }

    override fun close() {
        // rien à faire
    }

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        LOG.debug("Configure : $configs")
    }
}