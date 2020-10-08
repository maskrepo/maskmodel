package fr.convergence.proddoc.model.metier

import fr.convergence.proddoc.model.lib.serdes.LocalDateTimeSerializer
import io.quarkus.runtime.annotations.RegisterForReflection
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@RegisterForReflection
data class Lot(
    // identifiant positionne par myGreffe
    val identifiant: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val dateDemande: LocalDateTime,
    val codeUtilisateur: String,
    val identifiantFacture: String?,
    val sortieProduit: String?,
    val indicRestitutionDifferee: String?,
    val sequenceCollectionFacture: String?
)
