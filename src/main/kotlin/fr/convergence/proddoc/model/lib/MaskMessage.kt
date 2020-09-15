package fr.convergence.proddoc.model.lib


import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Serializable
class MaskEntete(
    val idUnique: String,
    val idLot: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class)
    val dateHeureDemande: LocalDateTime,
    val idEmetteur: String,
    val idGreffe: String,
    val typeDemande: String,
)


@Serializable
class MaskMessage(
    val entete: MaskEntete,
    var objetMetier: JsonElement
) {
    inline fun <reified T> recupererObjetMetier(): T {
        return Json.decodeFromJsonElement(objetMetier)
    }

    companion object MaskMessageBuilder {

        inline fun <reified T> of(payload: T, idEmetteur: String, idGreffe: String, typeDemande: String): MaskMessage {
            val maskEntete = MaskEntete(
                UUID.randomUUID().toString(),
                null,
                LocalDateTime.now(),
                idEmetteur,
                idGreffe,
                typeDemande
            )

            return MaskMessage(maskEntete, Json.parseToJsonElement(Json.encodeToString(payload)))
        }
    }
}