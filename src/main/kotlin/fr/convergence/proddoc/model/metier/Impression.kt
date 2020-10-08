package fr.convergence.proddoc.model.metier

import io.quarkus.runtime.annotations.RegisterForReflection
import kotlinx.serialization.Serializable

/**
 * pour faire bonne impression !
 */
@Serializable
@RegisterForReflection
class DemandeImpression (
    /**
     * L'URL absolue pour accèder au fichier via un stream
     */
    val urlFichierAImprimer: String,
    /**
     * L'URL de callback pour confirmer l'état de l'impression
     */
    val urlCallback : String?
)

@Serializable
@RegisterForReflection
class RetourImpression (
        /**
         * message détaillant le retour
         */
        val messageRetour :String
)

@Serializable
@RegisterForReflection
class RetourImpressionMyGreffe (
        /**
         * L'URL absolue du fichier qui devrait être imprimé
         */
        val urlFichierAImprimer: String,
        /**
         * message détaillant le retour
         */
        val messageRetour :String
)