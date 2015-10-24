package com.smiteworks.fantasygrounds;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 8/30/2015.
 */
public class FGNPCElementTest {

    @Test
    public void testCreateFromCreature() throws Exception {
        // Arrange
        net.monkeybutts.creature.Creature creature = new net.monkeybutts.creature.Creature();
        creature.parseFile("test.txt");
        Document doc = FGNpcElement.createDocument();

        // Act
        Element creatureElement = FGNpcElement.createFromCreature(doc, creature);

        // Assert
        FGNpcElement.prettyPrint(doc);
    }

    @Test
    public void testGetSingleAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcElement.getSingleAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18 (1d8+9/×3) or mwk spear +16 (1d8+6/×3)", result);
    }

    @Test
    public void testGetSingleAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcElement.getSingleAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13 ranged (1d8+3/×3)", result);
    }

    @Test
    public void testGetFullAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcElement.getFullAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18/+13 (1d8+9/×3) or mwk spear +16/+11 (1d8+6/×3)", result);
    }

    @Test
    public void testGetFullAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcElement.getFullAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13/+8 ranged (1d8+3/×3)", result);
    }
}