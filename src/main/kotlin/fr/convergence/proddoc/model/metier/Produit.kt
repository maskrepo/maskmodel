package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable


@Serializable
data class Produit(
    val typeEvenement: String, // PRODUIT ou evenement type GO_INTERPRETATION ( pour gerer la chaine d execution )
    val evenement: Evenement
)


@Serializable
data class Evenement(
    val idLot: String,
    val codeProduit: String, // RCS_KBIS,etc,...
    val mapObjetMetier: Map<String, String>? = null, //   {"REGISTRE": 75302}
    val listeTypesLignesGerees: List<String>? = null, //
    val listeIndicateur: List<String>? = null, //["VISUKBIS"]
    val nombreExemplaires: Int? = null,
    val pourApostille: Boolean? = null,
    val produit: String? = null,
    val sortieLot: String? = null,
    val reference: String? = null,
    val listeIndicateurEntite: List<String>? = null,
    val sortieEdition: String? = null,
    val mapSortieDestinataire: Map<String, String>? = null,
    val description: String? = null,
    val descriptionKbis: String? = null,
    val typeDocumentGed: String? = null,
    val dateTarif: String? = null,
    val mapDestinataires: Map<String, String>? = null,
    val listSortieDestinataire: List<String>? = null
)