package fr.convergence.proddoc.model.lib.serdes

import fr.convergence.proddoc.model.lib.obj.MaskEntete
import fr.convergence.proddoc.model.lib.obj.MaskMessage
import io.vertx.core.logging.Logger
import io.vertx.core.logging.LoggerFactory
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class MaskMessageSerDesTest {

    private lateinit var maskMessageSerDes: MaskMessageSerDes

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(MaskMessageSerDesTest::class.java)
    }

    @BeforeEach
    fun configureSystemUnderTest() {
        maskMessageSerDes = MaskMessageSerDes()
    }

    @Test
    internal fun shouldNotDeserializeIfNull() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            maskMessageSerDes.deserialize("topic", null)
        }
    }

    @Test
    internal fun shouldNotDeserializeIfEmpty() {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            maskMessageSerDes.deserialize("topic", "".toByteArray())
        }
    }

    @Test
    internal fun shouldNotDeserializeIfNotCompliant() {
        val message = "{faux: \"hjhjk\"}"
        Assertions.assertThrows(SerializationException::class.java) {
            maskMessageSerDes.deserialize("topic", message.toByteArray())
        }
    }

    @Test
    internal fun shouldDeserialize() {
        val message =
            "{\"entete\":{\"idUnique\":\"94d96412-7813-46e5-9205-8a1549119f3a\",\"idLot\":null,\"dateHeureDemande\":\"2020-09-15T17:33:41.837\",\"idEmetteur\":\"emetteur\",\"idGreffe\":\"greffe\",\"typeDemande\":\"demande de kbis\"},\"objetMetier\":{\"numeroGestion\":\"2012B00012\"},\"reponse\":null}"
        val deserializedObject = maskMessageSerDes.deserialize("topic", message.toByteArray())
        Assertions.assertNotNull(deserializedObject)
        Assertions.assertEquals("2012B00012", deserializedObject.recupererObjetMetier<KbisDemandeTest>().numeroGestion)
        Assertions.assertEquals("94d96412-7813-46e5-9205-8a1549119f3a", deserializedObject.entete.idUnique)
    }

    @Test
    internal fun shouldSerialize() {
        var maskEntete = MaskEntete("", "", LocalDateTime.now(), "", "", "")
        var maskMessageQuestionOrigine = MaskMessage(maskEntete, Json.parseToJsonElement("{}"), null)
        val maskMessage =
            MaskMessage.question(KbisDemandeTest("2012B00017", LocalDateTime.now()), maskMessageQuestionOrigine)

        val serializedObject = maskMessageSerDes.serialize("topic", maskMessage)

        Assertions.assertNotNull(serializedObject)
        Assertions.assertTrue(String(serializedObject).contains("2012B00017"))
        LOG.info(String(serializedObject))
    }
}

@Serializable
class KbisDemandeTest(
    /**
     * Numero de gestion sur 10 carcatères de la forme 2010B00012
     */
    val numeroGestion: String,

    @Serializable(with = LocalDateTimeSerializer::class)
    val dateHeureDemande: LocalDateTime? = null
)