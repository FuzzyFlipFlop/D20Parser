package net.monkeybutts.creature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class TacticsTest {
    @Test
    public void testParse() throws Exception {
        // Arrange
        String input = "During Combat Efrixes follows Bescaylie’s orders in combat,\n" +
                "alternating use of his breath weapon with melee attacks.\n" +
                "Morale Efrixes fights as long as Bescaylie does. If Bescaylie is\n" +
                "slain, Efrixes is overwhelmed with wrath and fights to the\n" +
                "death, focusing on killing her murderer.";

        Tactics tactics = new Tactics();

        // Act
        tactics.parse(input);

        // Assert
        assertEquals(input, tactics.getText());
    }
}