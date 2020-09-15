package fr.convergence.proddoc.model.lib

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class MaskTable(val value: String = "")