package net.monkeybutts.creature;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Created by Stimpyjc on 10/11/2015.
 */
public class SpellPreparedSectionTest {

    @Test
    public void testArcaneParse() throws Exception {
        // Arrange
        String input = "(CL 13th;\n" +
                "concentration +20)\n" +
                "7th—quickened dispel magic, prismatic\n" +
                "spray, reverse gravity (2)\n" +
                "6th—chain lightning (DC 23), disintegrate\n" +
                "(DC 24), flesh to stone (2, DC 24), quickened\n" +
                "glitterdust (DC 19)\n" +
                "5th—baleful polymorph (3, DC 23), hungry pitAPG (DC 22),\n" +
                "extended mass reduce person (DC 23), quickened shield\n" +
                "4th—black tentacles, extended haste, ice storm, mnemonic\n" +
                "enhancer (2), shout (DC 21), wall of fire\n" +
                "3rd—dispel magic, fireball (DC 20), lightning bolt (DC 20),\n" +
                "pain strikeAPG (DC 20), slow (2, DC 21), twilight knifeAPG,\n" +
                "wind wall\n" +
                "2nd—blindness/deafness (DC 19), flaming sphere (DC 19),\n" +
                "extended mage armor, pyrotechnics (DC 20), scorching ray,\n" +
                "shatter, whispering wind (2)\n" +
                "1st—burning hands (DC 18), erase, grease (DC 19), magic\n" +
                "missile (2), ray of enfeeblement (DC 18), reduce person (2,\n" +
                "DC 19)\n" +
                "0 (at will)—detect magic, light, mage hand, mending\n" +
                "Thassilonian Specialization transmutation; Opposition\n" +
                "Schools enchantment, illusion";

        SpellPreparedSection section = new SpellPreparedSection();

        // Act
        section.parse(input);

        // Assert
        assertEquals("13th", section.getCasterLevel());
        assertEquals("+20", section.getConcentration());
        assertThat(section.getLevel7Spells(), contains("quickened dispel magic", "prismatic spray", "reverse gravity (2)"));
        assertThat(section.getLevel6Spells(), contains("chain lightning (DC 23)", "disintegrate (DC 24)", "flesh to stone (2, DC 24)", "quickened glitterdust (DC 19)"));
        assertThat(section.getLevel5Spells(), contains("baleful polymorph (3, DC 23)", "hungry pit (APG, DC 22)", "extended mass reduce person (DC 23)", "quickened shield"));
        assertThat(section.getLevel4Spells(), contains("black tentacles", "extended haste", "ice storm", "mnemonic enhancer (2)", "shout (DC 21)", "wall of fire"));
        assertThat(section.getLevel3Spells(), contains("dispel magic", "fireball (DC 20)", "lightning bolt (DC 20)", "pain strike (APG, DC 20)", "slow (2, DC 21)", "twilight knife (APG)", "wind wall"));
        assertThat(section.getLevel2Spells(), contains("blindness/deafness (DC 19)", "flaming sphere (DC 19)", "extended mage armor", "pyrotechnics (DC 20)", "scorching ray", "shatter", "whispering wind (2)"));
        assertThat(section.getLevel1Spells(), contains("burning hands (DC 18)", "erase", "grease (DC 19)", "magic missile (2)", "ray of enfeeblement (DC 18)", "reduce person (2, DC 19)"));
        assertThat(section.getLevel0Spells(), contains("detect magic", "light", "mage hand", "mending"));
        assertEquals("Thassilonian Specialization transmutation; Opposition Schools enchantment, illusion", section.getSpecialization());
    }

    @Test
    public void testDivineParse() throws Exception {
        // Arrange
        String input = "(CL 15th; concentration +20)\n" +
                "8th— cloak of chaosD (DC 23), rift of ruinBOTD2 (DC 23)\n" +
                "7th—destruction (DC 22), insanityD (DC 22), mass cure serious\n" +
                "wounds (DC 22)\n" +
                "6th—blade barrier (DC 21), heal, heroes’ feast, phantasmal\n" +
                "killerD (DC 21)\n" +
                "5th—breath of life (2), dispel lawD, greater command (2, DC 20),\n" +
                "mass cure light wounds (DC 20)\n" +
                "4th—air walk, confusionD (DC 19), cure critical wounds,\n" +
                "freedom of movement, greater magic weapon (2)\n" +
                "3rd—bestow curse (DC 18), blindness/deafness (DC 18), cure\n" +
                "serious wounds (2), magic circle against lawD, magic vestment\n" +
                "2nd—cure moderate wounds (2), hold person (DC 17), resist\n" +
                "energy, silence (DC 17), touch of idiocyD\n" +
                "1st—command (DC 16), cure light wounds (3), lesser\n" +
                "confusionD (DC 16), obscuring mist, shield of faith\n" +
                "0 (at will)—bleed (DC 15), detect magic, read magic, stabilize\n" +
                "D Domain spell; Domains Chaos, Madness";

        SpellPreparedSection section = new SpellPreparedSection();

        // Act
        section.parse(input);

        // Assert
        assertEquals("15th", section.getCasterLevel());
        assertEquals("+20", section.getConcentration());
        assertThat(section.getLevel8Spells(), contains("cloak of chaos (D, DC 23)", "rift of ruin (BOTD2, DC 23)"));
        assertThat(section.getLevel7Spells(), contains("destruction (DC 22)", "insanity (D, DC 22)", "mass cure serious wounds (DC 22)"));
        assertThat(section.getLevel6Spells(), contains("blade barrier (DC 21)", "heal", "heroes’ feast", "phantasmal killer (D, DC 21)"));
        assertThat(section.getLevel5Spells(), contains("breath of life (2)", "dispel law (D)", "greater command (2, DC 20)", "mass cure light wounds (DC 20)"));
        assertThat(section.getLevel4Spells(), contains("air walk", "confusion (D, DC 19)", "cure critical wounds", "freedom of movement", "greater magic weapon (2)"));
        assertThat(section.getLevel3Spells(), contains("bestow curse (DC 18)", "blindness/deafness (DC 18)", "cure serious wounds (2)", "magic circle against law (D)", "magic vestment"));
        assertThat(section.getLevel2Spells(), contains("cure moderate wounds (2)", "hold person (DC 17)", "resist energy", "silence (DC 17)", "touch of idiocy (D)"));
        assertThat(section.getLevel1Spells(), contains("command (DC 16)", "cure light wounds (3)", "lesser confusion (D, DC 16)", "obscuring mist", "shield of faith"));
        assertThat(section.getLevel0Spells(), contains("bleed (DC 15)", "detect magic", "read magic", "stabilize"));
        assertEquals("D Domain spell; Domains Chaos, Madness", section.getSpecialization());
    }
}