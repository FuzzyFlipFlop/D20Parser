package com.smiteworks.fantasygrounds;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 8/30/2015.
 */
public class FGCreatureElementTest {

    @Test
    public void testCreateFromCreature() throws Exception {
        // Arrange
        net.monkeybutts.creature.Creature creature = new net.monkeybutts.creature.Creature();
        creature.parseFile("test.txt");
        Document doc = FGCreatureElement.createDocument();

        // Act
        Element creatureElement = FGCreatureElement.createFromCreature(doc, creature);

        // Assert
        FGCreatureElement.prettyPrint(doc);
    }

    @Test
    public void testGetSingleAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGCreatureElement.getSingleAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18 (1d8+9/×3) or mwk spear +16 (1d8+6/×3)", result);
    }

    @Test
    public void testGetSingleAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGCreatureElement.getSingleAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13 ranged (1d8+3/×3)", result);
    }

    @Test
    public void testGetFullAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGCreatureElement.getFullAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18/+13 (1d8+9/×3) or mwk spear +16/+11 (1d8+6/×3)", result);
    }

    @Test
    public void testGetFullAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGCreatureElement.getFullAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13/+8 ranged (1d8+3/×3)", result);
    }
}