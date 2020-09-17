package fr.convergence.proddoc.model.lib.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
annotation class MaskTable(val value: String = "")