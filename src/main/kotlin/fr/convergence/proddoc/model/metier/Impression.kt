package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

/**
 * pour faire bonne impression !
 */
@Serializable
class DemandeImpression(
        /**
         * lien entre serveur d'impression et myGreffe
         */
        val IDsortieDocument: String,
        /**
         * L'URL absolue pour accéder au fichier via un stream
         */
        val urlFichierAImprimer: String,

        val rectoVerso: Boolean,
        /**
         * nom de l'imprimante de sortie à utiliser
         */
        val nomImprimante: String,

        val nomBacEntree: String,
        val nbExemplaires: Int,
)

@Serializable
class RetourImpression(
        /**
         * message en français détaillant le retour
         */
        val messageRetour: String
)

@Serializable
class RetourImpressionMyGreffe(
        val IDsortieDocument: String,
        /**
         * L'URL absolue du fichier qui devrait être imprimé
         */
        val urlFichierAImprimer: String,
        /**
         * Etat du retour True = OK et False = KO
         */
        val etatRetour: Boolean
)