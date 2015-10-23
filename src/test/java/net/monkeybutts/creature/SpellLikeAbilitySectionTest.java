package net.monkeybutts.creature;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellLikeAbilitySectionTest  {
    @Test
    public void testParse() throws Exception {
        // Arrange
        String input = "(CL 15th; concentration +20)\n" +
                "Constant—invisibility purge\n" +
                "At will—death knell (DC 17), prayer\n" +
                "3/day—demand (DC 23), dimensional anchor, divination,\n" +
                "dominate person (DC 20), greater command (DC 20)\n" +
                "1/day—commune, dream, unhallow";

        SpellLikeAbilitySection section = new SpellLikeAbilitySection();

        // Act
        section.parse(input);

        // Assert
        assertEquals("15th", section.getCasterLevel());
        assertEquals("+20", section.getConcentration());
        assertThat(section.getConstantSpells(), contains("invisibility purge"));
        assertThat(section.getAtWillSpells(), contains("death knell (DC 17)", "prayer"));
        assertThat(section.getPerDay3Spells(), contains("demand (DC 23)", "dimensional anchor", "divination", "dominate person (DC 20)", "greater command (DC 20)"));
        assertThat(section.getPerDay1Spells(), contains("commune", "dream", "unhallow"));
    }
}