package net.monkeybutts.creature;

import org.junit.Test;

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
        assertEquals("CE Large dragon (cold)", header.getType());
        assertEquals("+5", header.getInitiative());
        assertEquals("darkvision 60 ft., low-light vision, scent, snow vision", header.getSenses());
        assertEquals("+10", header.getPerception());
	}
}
