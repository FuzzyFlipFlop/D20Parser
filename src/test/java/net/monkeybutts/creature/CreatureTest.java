package net.monkeybutts.creature;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CreatureTest {
	@Test
    public void testParseFile() throws Exception {
    	// Arrange
		Creature creature = new Creature();
		
		// Act
		creature.parseFile("test.txt");
		
		// Assert
		assertNotNull(creature.getHeader());
		assertNotNull(creature.getDefense());
		assertNotNull(creature.getOffense());
	}
}
