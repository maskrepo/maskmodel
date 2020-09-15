package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

@Serializable
class KbisDemande(
    /**
     * Numero de gestion sur 10 carcatères de la forme 2010B00012
     */
    val numeroGestion: String
)