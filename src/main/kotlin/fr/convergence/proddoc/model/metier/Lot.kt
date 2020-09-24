package fr.convergence.proddoc.model.metier

import fr.convergence.proddoc.model.lib.serdes.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Lot(
    val identifiant: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val dateDemande: LocalDateTime,
    val codeUtilisateur: String,
    val identifiantFacture: String?,
    val sortieProduit: String?,
    val indicRestitutionDifferee: String?,
    val sequenceCollectionFacture: String?
)
