package net.monkeybutts.creature;


/**
 * Created by Stimpyjc on 8/29/2015.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {
    @Test
    public void testParse() throws Exception {
        // Arrange
        String input = "Str 12, Dex 17, Con 14, Int 10, Wis 8, Cha 12\n" +
                "Base Atk +2; CMB +3; CMD 16\n" +
                "Feats Combat Casting, Eschew Materials, Lightning Reflexes\n" +
                "Skills Disguise +5, Fly +8, Knowledge (arcana) +4,\n" +
                "Perception +1 (+3 in dim light or darkness), Spellcraft +4,\n" +
                "Stealth +5 (+7 in dim light or darkness)\n" +
                "Languages Common, Strix\n" +
                "SQ bloodline arcana (change energy damage spells to\n" +
                "match bloodline energy), nocturnal, suspicious\n" +
                "Combat Gear potion of cure light wounds; Other Gear\n" +
                "darts (5), mwk spiked gauntlet, amulet of natural\n" +
                "armor +1, 45 gp";

        Statistics statistics = new Statistics();

        // Act
        statistics.parse(input);

        // Assert
        assertEquals("12", statistics.getStrength());
        assertEquals("17", statistics.getDexterity());
        assertEquals("14", statistics.getConstitution());
        assertEquals("10", statistics.getIntelligence());
        assertEquals("8", statistics.getWisdom());
        assertEquals("12", statistics.getCharisma());
        assertEquals("+2", statistics.getBaseAttack());
        assertEquals("+3", statistics.getCmb());
        assertEquals("16", statistics.getCmd());
        assertEquals("Combat Casting, Eschew Materials, Lightning Reflexes", statistics.getFeats());
        assertEquals("Disguise +5, Fly +8, Knowledge (arcana) +4, " +
                "Perception +1 (+3 in dim light or darkness), Spellcraft +4, " +
                "Stealth +5 (+7 in dim light or darkness)", statistics.getSkills());
        assertEquals("Common, Strix", statistics.getLanguages());
        assertEquals("bloodline arcana (change energy damage spells to " +
                "match bloodline energy), nocturnal, suspicious", statistics.getSq());
        assertEquals("potion of cure light wounds; Other Gear " +
                "darts (5), mwk spiked gauntlet, amulet of natural " +
                "armor +1, 45 gp", statistics.getGear());

    }
}