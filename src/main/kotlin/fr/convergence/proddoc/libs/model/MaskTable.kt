package fr.convergence.proddoc.libs.model

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class MaskTable(val value: String = "")