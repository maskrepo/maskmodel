package fr.convergence.proddoc.model.metier

import kotlinx.serialization.Serializable

@Serializable
class KbisDemande(
    /**
     * Numero de gestion sur 10 carcatères de la forme 2010B00012
     */
    val numeroGestion :String,
    /**
     * Apostille sur 3 caractère de la forme OUI/NON
     */
    val apostille :String,
    /***
     * Sceau sur 3 caractère de la forme OUI/NON
     */
    val sceau :String,
    /**
     * Signature sur 3 caractère de la forme OUI/NON
     */
    val signature :String
)