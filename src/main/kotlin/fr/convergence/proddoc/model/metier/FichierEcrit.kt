package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

@Serializable
class FichierEcrit(
    /**
     * URL absolue du fichier sur le FileSystem
     */
    val fichierURLAbs :String,
    /**
     * URL relative du fichier
     */
    val fichierURLRel :String,
    /**
     * identifiant métier unique du fichier
     */
    val idMetierFichier :String
)

@Serializable
class FichierAccessible(
    /**
     * message donnant l'URL d'accès au fichier enregistré sur le disque
     */
    val messageRetour :String
)