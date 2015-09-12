package net.monkeybutts.creature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class EcologyTest {
    @Test
    public void testParse() throws Exception {
        // Arrange
        String input = "Environment cold mountains\n" +
                "Organization solitary, pair, or rampage (3–12)\n" +
                "Treasure standard\n" +
                "\n" +
                "Degenerate cousins of white dragons, frost dragons are ferocious predators. They are larger than other drakes, reaching heights of up to 16 feet and weighing upward of 2,500 pounds. Their wide, clawed feet enable them to easily burrow through snow, though not through dirt or clay.\n" +
                "\n" +
                "Young frost drakes form adolescent hunting packs divided along gender lines, but older frost drakes are usually encountered in mated pairs. Frost drakes mate for life, leaving their packs when they find a suitable mate. Mated pairs make a nest together, and the female lays a clutch of two to five eggs. Both parents care for their offspring when they hatch, and families usually form small packs until the young reach maturity at 5 years of age. At this point, the parents abandon their offspring, usually laying a new clutch of eggs in a new nest elsewhere, and leaving the f ledgling drakes to find their own adolescent packs to join.";

        Ecology ecology = new Ecology();

        // Act
        ecology.parse(input);

        // Assert
        assertEquals(input, ecology.getText());
    }
}