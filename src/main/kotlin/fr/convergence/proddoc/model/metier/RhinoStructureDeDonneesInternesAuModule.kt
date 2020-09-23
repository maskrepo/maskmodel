package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

@Serializable
data class MaskLot (
    val idLot : String,
    val dateDeReceptionDuLot: String,
    val codeUtilisateur: String,
    val produits : MutableList<MaskEvenement>?,
    val actions : MutableList<MaskAction>?,
    var peutOnDemarrerInterpretation : Boolean = false
)

@Serializable
data class MaskEvenement (
    val idEvenement : String?,
    val codeProduit : String, // RCS_KBIS,etc,...,ainsi que LANCEMENT_INTERPRETATION
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

@Serializable
data class MaskAction (
    val idEvenementDuquelDependCetteAction : String, // On doit le retrouver dans la liste des MaskActions de MaskLot
    val libelleAction : String,
    var actionEstElleRealisee : Boolean = false
)