package net.monkeybutts.creature;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellKnownSectionTest {
    @Test
    public void testParse() throws Exception {
        // Arrange
        String input = "(CL 15th; concentration +25)\n" +
                "7th (4)—greater scrying (DC 22), prismatic spray (DC 22)\n" +
                "6th (6)—chain lightning (DC 21), contingency, geas/quest\n" +
                "5th (7)—dominate person (DC 20), nightmare (DC 20),\n" +
                "sending, teleport\n" +
                "4th (7)—charm monster (DC 19), greater invisibility,\n" +
                "phantasmal killer (DC 19), wall of ice\n" +
                "3rd (7)—dispel magic, displacement, gaseous form, slow (DC 18)\n" +
                "2nd (7)—alter self, command undead (DC 17), detect thoughts\n" +
                "(DC 17), glitterdust (DC 17), scorching ray\n" +
                "1st (8)—charm person (DC 16), endure elements, grease (DC 16),\n" +
                "magic missile, reduce person (DC 16)\n" +
                "0 (at will)—bleed (DC 15), detect magic, light, mage hand,\n" +
                "message, open/close, prestidigitation, read magic, touch of\n" +
                "fatigue (DC 15)";

        SpellKnownSection section = new SpellKnownSection();

        // Act
        section.parse(input);

        // Assert
        assertEquals("15th", section.getCasterLevel());
        assertEquals("+25", section.getConcentration());
        assertEquals(4, section.getSpellsPerDay(SpellCategory.LEVEL7));
        assertThat(section.getSpellList(SpellCategory.LEVEL7), contains("greater scrying (DC 22)", "prismatic spray (DC 22)"));
        assertEquals(6, section.getSpellsPerDay(SpellCategory.LEVEL6));
        assertThat(section.getSpellList(SpellCategory.LEVEL6), contains("chain lightning (DC 21)", "contingency", "geas/quest"));
        assertEquals(7, section.getSpellsPerDay(SpellCategory.LEVEL5));
        assertThat(section.getSpellList(SpellCategory.LEVEL5), contains("dominate person (DC 20)", "nightmare (DC 20)", "sending", "teleport"));
        assertEquals(7, section.getSpellsPerDay(SpellCategory.LEVEL4));
        assertThat(section.getSpellList(SpellCategory.LEVEL4), contains("charm monster (DC 19)", "greater invisibility", "phantasmal killer (DC 19)", "wall of ice"));
        assertEquals(7, section.getSpellsPerDay(SpellCategory.LEVEL3));
        assertThat(section.getSpellList(SpellCategory.LEVEL3), contains("dispel magic", "displacement", "gaseous form", "slow (DC 18)"));
        assertEquals(7, section.getSpellsPerDay(SpellCategory.LEVEL2));
        assertThat(section.getSpellList(SpellCategory.LEVEL2), contains("alter self", "command undead (DC 17)", "detect thoughts (DC 17)", "glitterdust (DC 17)", "scorching ray"));
        assertEquals(8, section.getSpellsPerDay(SpellCategory.LEVEL1));
        assertThat(section.getSpellList(SpellCategory.LEVEL1), contains("charm person (DC 16)", "endure elements", "grease (DC 16)", "magic missile", "reduce person (DC 16)"));
        assertThat(section.getSpellList(SpellCategory.LEVEL0), contains("bleed (DC 15)", "detect magic", "light", "mage hand", "message", "open/close", "prestidigitation", "read magic", "touch of fatigue (DC 15)"));
    }
}