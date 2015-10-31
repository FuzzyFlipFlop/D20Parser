package com.smiteworks.fantasygrounds.npc;

import com.smiteworks.fantasygrounds.FGElement;
import com.smiteworks.fantasygrounds.npc.FGNpcElement;
import com.smiteworks.fantasygrounds.npc.FGNpcRootElement;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xmlunit.matchers.CompareMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 8/30/2015.
 */
public class FGNpcRootElementTest {

    @Test
    public void testCreateElement() throws Exception {
        // Arrange
        String expectedXml = ("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <npc>\n" +
                "<category baseicon=\"0\" decalicon=\"0\" mergeid=\"\" name=\"\"/>\n" +
                "</npc>");

        Document document = FGElement.createDocument();
        List<FGNpcElement> npcDetailElementList = new ArrayList<>();
        FGNpcRootElement element = new FGNpcRootElement(document, "name", "referenceName", npcDetailElementList);

        // Act
        Element resultElement = element.createElement();

        String result = FGElement.elementToString(resultElement);

        // Assert
        assertThat(result, CompareMatcher.isIdenticalTo(expectedXml));
    }

    /*
    @Test
    public void testCreateFromCreature() throws Exception {
        // Arrange
        net.monkeybutts.creature.Creature creature = new net.monkeybutts.creature.Creature();
        creature.parseFile("test.txt");
        Document doc = FGNpcRootElement.createDocument();

        // Act
        Element creatureElement = FGNpcRootElement.createFromCreature(doc, creature);

        // Assert
        FGNpcRootElement.prettyPrint(doc);
    }

    @Test
    public void testGetSingleAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcRootElement.getSingleAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18 (1d8+9/×3) or mwk spear +16 (1d8+6/×3)", result);
    }

    @Test
    public void testGetSingleAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcRootElement.getSingleAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13 ranged (1d8+3/×3)", result);
    }

    @Test
    public void testGetFullAttack() throws Exception {
        // Arrange
        String melee = ("+1 lance +18/+13 (1d8+9/×3) or\n" +
                "mwk spear +16/+11 (1d8+6/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcRootElement.getFullAttack(melee, false);

        // Assert
        assertEquals("+1 lance +18/+13 (1d8+9/×3) or mwk spear +16/+11 (1d8+6/×3)", result);
    }

    @Test
    public void testGetFullAttackRanged() throws Exception {
        // Arrange
        String ranged = ("mwk composite longbow\n" +
                "+13/+8 (1d8+3/×3)").replaceAll("\\s+", " ");

        // Act
        String result = FGNpcRootElement.getFullAttack(ranged, true);

        // Assert
        assertEquals("mwk composite longbow +13/+8 ranged (1d8+3/×3)", result);
    }
    */
}