package fr.convergence.proddoc.libs.model

@MaskTable("sortie_produit_truc")
class Produit {

    var cle: String? = null
    var valeur: String? = null
    var identifiant: String? = null
    var code_domaine: String? = null
    var code_sous_domaine: String? = null
    var chrono: String? = null
    var type: String? = null
    var type_java: String? = null
    var commentaire: String? = null
    var constante_java: String? = null
    var indic_national: String? = null
    var condition: String? = null
    var nom_table_reference: String? = null
    var timestamp = System.currentTimeMillis()
    var monpetitproduit : String? = null

    override fun toString(): String {
        return "fr.convergence.proddoc.libs.model.Produit(${code_domaine}\\${code_sous_domaine}\\${cle}\\${chrono} => \"$valeur\")"

    }

}