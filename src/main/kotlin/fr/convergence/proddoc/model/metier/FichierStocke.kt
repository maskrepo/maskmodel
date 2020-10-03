package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

@Serializable
class FichierStocke(
    /**
     * Un identifiant connu de Stinger seul (pour référence)
     */
    val identifiantStockage: String,
    /**
     * L'URL absolue pour accèder au fichier via un stream
     */
    val urlAcces: String,
    /**
     * L'URL absolue pour accèder au fichier via un navigateur
     */
    val urlAccesNavigateur: String,
    /**
     * L'URL relative pour accèder au fichier (sans le nom du serveur/port)
     */
    val urlRelative: String,
)
