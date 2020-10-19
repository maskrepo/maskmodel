package fr.convergence.proddoc.model.metier

import fr.convergence.proddoc.model.lib.serdes.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Lot(
    // identifiant positionne par myGreffe
    val identifiant: String,
    @Serializable(with = LocalDateSerializer::class)
    val dateDemande: LocalDate,
    val codeUtilisateur: String,
    val identifiantFacture: String?,
    val sortieProduit: String?,
    val indicRestitutionDifferee: String?,
    val sequenceCollectionFacture: String?
)
