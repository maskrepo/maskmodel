package fr.convergence.proddoc.model.metier

import io.quarkus.runtime.annotations.RegisterForReflection
import kotlinx.serialization.Serializable

@Serializable
@RegisterForReflection
class KbisDemande(
    /**
     * Numero de gestion sur 10 carcatères de la forme 2010B00012
     */
    val numeroGestion :String,
    /**
     * Apostille sur 3 caractère de la forme OUI/NON
     */
    val avecApostille :Boolean,
    /***
     * Sceau sur 3 caractère de la forme OUI/NON
     */
    val avecSceau :Boolean,
    /**
     * Signature sur 3 caractère de la forme OUI/NON
     */
    val avecSignature :Boolean
)

@Serializable
@RegisterForReflection
class KbisRetour(
        /**
         * message détaillant le retour : URL par exemple
         */
        val messageRetour :String
)