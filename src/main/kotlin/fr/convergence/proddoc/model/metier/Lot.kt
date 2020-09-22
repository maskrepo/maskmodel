package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

@Serializable
data class Lot(
    val identifiant: String,
    val dateDemande: String,
    val codeUtilisateur: String,
    val identifiantFacture: String?,
    val sortieProduit: String?,
    val indicRestitutionDifferee: String?,
    val sequenceCollectionFacture: String?
)
