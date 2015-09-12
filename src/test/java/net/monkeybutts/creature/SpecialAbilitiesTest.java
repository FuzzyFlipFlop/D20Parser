package net.monkeybutts.creature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class SpecialAbilitiesTest {
    @Test
    public void testParse() throws Exception {
        // Arrange
        String input = "Freezing Mist Breath (Su)\n" +
                "\n" +
                "A frost drake can, as a standard action, spit a ball of liquid that bursts into a cloud of freezing mist. This attack has a range of 60 feet and deals 7d6 points of cold damage (DC 18 Reflex half) to all creatures in a 20-foot-radius spread. The mist cakes all surfaces in the area with a sheet of slippery ice that turns the area into difficult terrain for 2d4 rounds, after which the ice cracks or melts enough to revert to the normal terrain features in the area. Once a frost drake has used its freezing mist breath, it cannot do so again for 1d6 rounds. The Reflex save is Constitution-based.\n" +
                "\n" +
                "Icewalking (Ex)\n" +
                "\n" +
                "This ability works like spider climb, but the surfaces the drake climbs must be icy. It can move across icy surfaces without penalty and does not need to make Acrobatics checks to run or charge on ice.\n" +
                "\n" +
                "Speed Surge (Ex)\n" +
                "\n" +
                "Three times per day as a swift action, a frost drake may draw on its draconic heritage for a boost of strength and speed to take an additional move action in that round.\n" +
                "\n" +
                "Snow Vision (Ex)\n" +
                "\n" +
                "A frost drake can see perfectly well in snowy conditions, and does not take any penalties on Perception checks while in snow.";

        SpecialAbilities specialAbilities = new SpecialAbilities();

        // Act
        specialAbilities.parse(input);

        // Assert
        assertEquals(input, specialAbilities.getText());
    }
}