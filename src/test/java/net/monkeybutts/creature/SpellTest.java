package net.monkeybutts.creature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by mike on 11/1/15.
 */
public class SpellTest {
    @Test
    public void testCreateFromString1() throws Exception {
        // Arrange
        String input = "spell";

        // Act
        Spell result = Spell.createFromString(input);

        // Assert
        assertEquals("spell", result.getName());
        assertNull(result.getReference());
        assertEquals(1, result.getCount());
        assertNull(result.getDifficultyClass());
    }

    @Test
    public void testCreateFromString2() throws Exception {
        // Arrange
        String input = "spellREF";

        // Act
        Spell result = Spell.createFromString(input);

        // Assert
        assertEquals("spell", result.getName());
        assertEquals("REF", result.getReference());
        assertEquals(1, result.getCount());
        assertNull(result.getDifficultyClass());
    }

    @Test
    public void testCreateFromString3() throws Exception {
        // Arrange
        String input = "spellREF (2)";

        // Act
        Spell result = Spell.createFromString(input);

        // Assert
        assertEquals("spell", result.getName());
        assertEquals("REF", result.getReference());
        assertEquals(2, result.getCount());
        assertNull(result.getDifficultyClass());
    }

    @Test
    public void testCreateFromString4() throws Exception {
        // Arrange
        String input = "spellREF (2, DC 15)";

        // Act
        Spell result = Spell.createFromString(input);

        // Assert
        assertEquals("spell", result.getName());
        assertEquals("REF", result.getReference());
        assertEquals(2, result.getCount());
        assertEquals(15, result.getDifficultyClass().intValue());
    }
}