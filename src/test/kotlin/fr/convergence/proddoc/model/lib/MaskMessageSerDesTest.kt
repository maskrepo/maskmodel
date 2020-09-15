package fr.convergence.proddoc.model.lib

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

internal class MaskMessageSerDesTest {

    companion object {
        private val LOG = LoggerFactory.getLogger(MaskMessageSerDesTest::class.java)
    }

    private lateinit var maskMessageSerDes: MaskMessageSerDes

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
        LOG.info("Message : $message")
        Assertions.assertThrows(SerializationException::class.java) {
            maskMessageSerDes.deserialize("topic", message.toByteArray())
        }
    }

    @Test
    internal fun shouldDeserialize() {
        val message =
            "{\"entete\":{\"idUnique\":\"94d96412-7813-46e5-9205-8a1549119f3a\",\"idLot\":null,\"dateHeureDemande\":\"2020-09-15T17:33:41.837\",\"idEmetteur\":\"emetteur\",\"idGreffe\":\"greffe\",\"typeDemande\":\"demande de kbis\"},\"objetMetier\":{\"numeroGestion\":\"2012B00012\"}}"
        LOG.info("Message : $message")
        val deserializedObject = maskMessageSerDes.deserialize("topic", message.toByteArray())
        Assertions.assertNotNull(deserializedObject)
        Assertions.assertEquals("2012B00012", deserializedObject.recupererObjetMetier<KbisDemandeTest>().numeroGestion)
        Assertions.assertEquals("94d96412-7813-46e5-9205-8a1549119f3a", deserializedObject.entete.idUnique)
    }

    @Test
    internal fun shouldSerialize() {
        val maskMessage =
            MaskMessage.of(KbisDemandeTest("2012B00012", LocalDateTime.now()), "emetteur", "greffe", "demande de kbis")
        val serializedObject = maskMessageSerDes.serialize("topic", maskMessage)
        Assertions.assertNotNull(serializedObject)
        println("json : ${String(serializedObject)}")
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