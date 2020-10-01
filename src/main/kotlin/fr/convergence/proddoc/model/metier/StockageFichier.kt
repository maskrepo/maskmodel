package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

@Serializable
class StockageFichier(
        /**
         * identifiant métier unique du fichier
         */
        val idMetierFichier: String,
        /**
         * URL absolue du fichier sur le FileSystem
         */
        val fichierURLAbs: String?,
        /**
         * URL relative du fichier
         */
        val fichierURLRel: String?,
        /**
         * paramètres de requête http éventuels
         */
        val Parametres: Map<String, String> ?,
        /**
         * eventuel chemin complet sur le file system
         */
        val fichierFileSystemURL :String?
)

@Serializable
class FichierAccessible(
    /**
     * message donnant l'URL d'accès au fichier enregistré sur le disque
     */
    val messageRetour :String
)