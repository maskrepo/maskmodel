package fr.convergence.proddoc.model.metier

import fr.convergence.proddoc.model.lib.obj.MaskMessage
import io.quarkus.runtime.annotations.RegisterForReflection
import kotlinx.serialization.Serializable

/**
 * Permet de faire une demande de stockage de fichier à Stinger
 * Message transmis sur le topic "STOCKER_FICHIER_DEMANDE"
 */
@Serializable
@RegisterForReflection
class DemandeStockageFichier(
    /**
     * URL que doit appeler Stringer pour récupérer le stream sur le fichier à stocker
     */
    val urlCallback: String,
    /**
     * Le message qui doit être transmis dans le POST sur l'URL de callback
     */
    val maskMessage: MaskMessage,
    /**
     * Le type de fichier à stocker : application/json, application/xml, etc...
     */
    val mediaType: String,
    /**
     * Une reference metier qui permettra d'identifier plus facilement le fichier une fois stocké ou lors de sa récupération
     */
    val referenceMetier: String
)