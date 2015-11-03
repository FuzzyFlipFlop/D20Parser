package net.monkeybutts.creature;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellSectionTest {
    @Test
    public void testParseSpells() throws Exception {
        // Arrange
        String input = "chain lightning (DC 23), disintegrate\n" +
                "(DC 24), flesh to stone (2, DC 24), quickened\n" +
                "glitterdust (DC 19)\n";

        SpellPreparedSection spellPreparedSection = new SpellPreparedSection();

        // Act
        List<Spell> result = spellPreparedSection.parseSpells(input);

        // Assert
        List<String> list = result.stream().map(Spell::toString).collect(Collectors.toList());
        assertThat(list, contains("chain lightning (DC 23)", "disintegrate (DC 24)", "flesh to stone (2, DC 24)", "quickened glitterdust (DC 19)"));
    }
}