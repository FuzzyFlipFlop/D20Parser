package net.monkeybutts.xml;

import net.monkeybutts.creature.Creature;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 8/30/2015.
 */
public class CreatureToFGXmlTest {

    @Test
    public void testCreateFromCreature() throws Exception {
        // Arrange
        Creature creature = new Creature();
        creature.parseFile("test.txt");
        Document doc = CreatureToFGXml.createDocument();

        // Act
        Element creatureElement = CreatureToFGXml.createFromCreature(doc, creature);

        // Assert
        CreatureToFGXml.prettyPrint(doc);
    }

    @Test
    public void testGetSingleAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = CreatureToFGXml.getSingleAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18 (1d8+9/×3) or mwk spear +16 (1d8+6/×3)", result);
    }

    @Test
    public void testGetSingleAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = CreatureToFGXml.getSingleAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13 ranged (1d8+3/×3)", result);
    }

    @Test
    public void testGetFullAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = CreatureToFGXml.getSingleAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18/+13 (1d8+9/×3) or mwk spear +16/+11 (1d8+6/×3)", result);
    }

    @Test
    public void testGetFullAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = CreatureToFGXml.getFullAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13/+8 ranged (1d8+3/×3)", result);
    }
}