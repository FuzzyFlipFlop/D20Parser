package net.monkeybutts.creature;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

public class HeaderTest {
	@Test
	public void testParse() throws Exception {
		// Arrange
		String input = ("Frost Drake\tCR 7\r\n" +
				"XP 3,200\n" +
				"CE Large dragon (cold)\n" +
				"Init +5; Senses darkvision 60 ft., low-light vision, scent, snow vision; Perception +10");

		Header header = new Header();
		
		// Act
		header.parse(input);

		// Assert
		assertEquals("Frost Drake", header.getName());
        assertEquals("7", header.getChallengeRating());
        assertEquals("3,200", header.getExperience());
		assertEquals("CE", header.getAlignment());
        assertEquals("Large dragon (cold)", header.getType());
        assertEquals("+5", header.getInitiative());
        assertEquals("darkvision 60 ft., low-light vision, scent, snow vision", header.getSenses());
        assertEquals("+10", header.getPerception());
	}

    @Test
    public void testParseClassLevel() throws Exception {
        // Arrange
        String input = ("BESCAYLIE CR 9\n" +
                "XP 6,400\n" +
                "Female Winterborn Triaxian\n" +
                "fighter (dragoon) 10\n" +
                "(Pathfinder RPG Ultimate\n" +
                "Combat 46 and see page 86)\n" +
                "LN Medium humanoid\n" +
                "(Triaxian)\n" +
                "Init +2; Senses low-light\n" +
                "vision; Perception +12");

        Header header = new Header();

        // Act
        header.parse(input);

        // Assert

        assertEquals("BESCAYLIE", header.getName());
        assertEquals("9", header.getChallengeRating());
        assertEquals("6,400", header.getExperience());
        assertThat(header.getClasses(), contains("FIGHTER"));
        assertEquals("LN", header.getAlignment());
        assertEquals("Medium humanoid (Triaxian)", header.getType());
        assertEquals("+2", header.getInitiative());
        assertEquals("low-light vision", header.getSenses());
        assertEquals("+12", header.getPerception());
    }
}
