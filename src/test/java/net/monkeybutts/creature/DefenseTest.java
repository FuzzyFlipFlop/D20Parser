package net.monkeybutts.creature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefenseTest {
	@Test
	public void testParse() throws Exception {
		// Arrange
		String input = "AC 20, touch 10, flat-footed 19 (+1 Dex, +10 natural, –1 size)\n" +
				"hp 84 (8d12+32)\n" +
				"Fort +10, Ref +7, Will +5\n" +
				"Immune cold, paralysis, sleep\n" +
				"Weaknesses vulnerability to fire";

		Defense defense = new Defense();

		// ACT
		defense.parse(input);

		// ASSERT
		assertEquals("20, touch 10, flat-footed 19 (+1 Dex, +10 natural, –1 size)", defense.getArmorClass());
		assertEquals("84", defense.getHitPoints());
		assertEquals("8d12+32", defense.getHitDice());
		assertEquals("+10", defense.getFortitudeSave());
		assertEquals("+7", defense.getReflexSave());
		assertEquals("+5", defense.getWillSave());
		assertEquals("cold, paralysis, sleep", defense.getImmune());
		assertEquals("vulnerability to fire", defense.getWeaknesses());
	}
}
