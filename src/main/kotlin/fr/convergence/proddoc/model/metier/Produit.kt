package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable


@Serializable
data class Produit(
    val typeEvenement : String, // PRODUIT ou evenement type GO_INTERPRETATION ( pour gerer la chaine d execution )
    val evenement: Evenement
)


@Serializable
data class Evenement (
    val idLot : String,
    val codeProduit : String, // RCS_KBIS,etc,...
    val mapObjetMetier : Map<String,String>?, //   {"REGISTRE": 75302}
    val listeTypesLignesGerees : List<String>?, //
    val listeIndicateur : List<String>?, //["VISUKBIS"]
    val nombreExemplaires : Int?,
    val pourApostille : Boolean?,
    val produit : String?,
    val sortieLot : String?,
    val reference : String?,
    val listeIndicateurEntite : List<String>?,
    val sortieEdition: String?,
    val mapSortieDestinataire : Map<String,String>?,
    val description : String?,
    val descriptionKbis : String?,
    val typeDocumentGed : String?,
    val dateTarif : String?,
    val mapDestinataires : Map<String,String>?,
    val listSortieDestinataire : List<String>?
)