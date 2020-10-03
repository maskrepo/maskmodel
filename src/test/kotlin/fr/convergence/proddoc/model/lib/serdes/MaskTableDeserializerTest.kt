package fr.convergence.proddoc.model.lib

import fr.convergence.proddoc.model.lib.serdes.MaskTableDeserialiseur
import fr.convergence.proddoc.model.table.ParametreTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MaskDeserializerTest {

    companion object {
        val TOPIC_TABLE_PARAMETRE = "dbserver1.gtc.p_parametre"
    }

    @Test
    internal fun shouldDeserializeClass() {
        val maskDeserializer = MaskTableDeserialiseur()
        var fileContent =
            MaskDeserializerTest::class.java.getResource("/fr.convergence.proddoc.model.lib.serdes/parametre_test.json")
                .readText()


        val testObject = maskDeserializer.deserialize(TOPIC_TABLE_PARAMETRE, fileContent.toByteArray())!!
        Assertions.assertNotNull(testObject)
        Assertions.assertEquals(testObject::class.java, ParametreTest::class.java)

        val testParametre: ParametreTest = testObject as ParametreTest
        Assertions.assertEquals("CLEF_TEST", testParametre.cle)
        Assertions.assertEquals("CLEF_VALEUR_Ë&€", testParametre.valeur)
    }

    @Test
    internal fun shouldFailIfTableNameNotFoundInJson() {
        val maskDeserializer = MaskTableDeserialiseur()

        Assertions.assertThrows(
            IllegalArgumentException::class.java,
            { maskDeserializer.deserialize(TOPIC_TABLE_PARAMETRE, "{ \"bad\" : \"format\" }".toByteArray())!! })
    }

    @Test
    internal fun shouldFailIfClassFromTableNameNotFound() {
        val maskDeserializer = MaskTableDeserialiseur()
        var fileContent =
            MaskDeserializerTest::class.java.getResource("/fr.convergence.proddoc.model.lib.serdes/parametre_test_unkown_table.json")
                .readText()

        val assertThrows = Assertions.assertThrows(
            IllegalArgumentException::class.java,
            { maskDeserializer.deserialize(TOPIC_TABLE_PARAMETRE, fileContent.toByteArray())!! }
        )
        Assertions.assertEquals(
            "No class with annotation @MaskTable(\"table_de_test_introuvable\") found",
            assertThrows.message
        )
    }
}