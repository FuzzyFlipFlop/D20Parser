package net.monkeybutts.creature;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OffenseTest {
	@Test
	public void testParse1() throws Exception {
		// Arrange
		String input = "Speed 20 ft., burrow 20 ft. (snow only), fly 60 ft. (average)\n" +
				"Melee bite +13 (2d6+6 plus 1d6 cold), tail slap +8 (1d8+3)\n" +
				"Space 10 ft.; Reach 10 ft.\n" +
				"Special Attacks freezing mist breath";

		Offense offense = new Offense();

		// Act
		offense.parse(input);

		// Assert
		assertEquals("20 ft., burrow 20 ft. (snow only), fly 60 ft. (average)", offense.getSpeed());
		assertEquals("bite +13 (2d6+6 plus 1d6 cold), tail slap +8 (1d8+3)", offense.getMelee());
		assertNull(offense.getRanged());
		assertEquals("10 ft.", offense.getSpace());
		assertEquals("10 ft.", offense.getReach());
		assertEquals("freezing mist breath", offense.getSpecialAttacks());
	}

    @Test
    public void testParse2() throws Exception {
		// Arrange
		String input = ("Speed 20 ft.\n" +
				"Melee mwk heavy mace +6 (1d8+4)\n" +
				"Ranged composite longbow +4 (1d8+2/×3)\n" +
				"Special Attacks hatred");

		Offense offense = new Offense();

		// Act
		offense.parse(input);

		// Assert
		assertEquals("20 ft.", offense.getSpeed());
		assertEquals("mwk heavy mace +6 (1d8+4)", offense.getMelee());
		assertEquals("composite longbow +4 (1d8+2/×3)", offense.getRanged());
		assertNull(offense.getSpace());
		assertNull(offense.getReach());
		assertEquals("hatred", offense.getSpecialAttacks());
    }

    @Test
    public void testParse3() throws Exception {
		// Arrange
        String input = ("Speed 30 ft., fly 60 ft. (average)\n" +
                "Melee mwk spiked gauntlet +4 (1d4+1)\n" +
                "Ranged dart +5 (1d4+1)\n" +
                "Special Attacks hatred\n" +
                "Bloodline Spell-Like Abilities (CL 4th; concentration +5)\n" +
                "4/day—elemental ray (1d6+2 fire)\n" +
                "Sorcerer Spells Known (CL 4th; concentration +5)\n" +
                "2nd (3/day)—scorching ray\n" +
                "1st (7/day)—burning hands (DC 12), disguise self, mage\n" +
                "armor, shocking grasp\n" +
                "0 (at will)—arcane mark, daze (DC 11), detect poison,\n" +
                "ghost sound (DC 11), mage hand, ray of frost\n" +
                "Bloodline elemental (fire)");

		Offense offense = new Offense();

		// Act
        offense.parse(input);

		// Assert
		assertEquals("30 ft., fly 60 ft. (average)", offense.getSpeed());
		assertEquals("mwk spiked gauntlet +4 (1d4+1)", offense.getMelee());
		assertEquals("dart +5 (1d4+1)", offense.getRanged());
		assertNull(offense.getSpace());
		assertNull(offense.getReach());
		assertEquals("hatred", offense.getSpecialAttacks());
    }

	@Test
	public void testParse4() throws Exception {
		// Arrange
		String input = ("Speed 30 ft.\n" +
				"Melee mwk spear +7/+2 (1d8–1/×3)\n" +
				"Ranged dart +9 (1d4–1)\n" +
				"Oracle Spells Known (CL 10th; concentration +14)\n" +
				"5th (3/day)—holy iceUM (DC 19), icy prisonUM (DC 19), mass\n" +
				"cure light wounds (DC 19)\n" +
				"4th (6/day)—cure critical wounds, freedom of movement,\n" +
				"ice storm, order’s wrath (DC 18)\n" +
				"3rd (7/day)—cure serious wounds, dispel magic, invisibility\n" +
				"purge, sleet storm, stone shape\n" +
				"2nd (7/day)—aid, arrow of lawUM (DC 16), cure moderate\n" +
				"wounds, frost fallUC (DC 16), sound burst (DC 16),\n" +
				"spiritual weapon\n" +
				"1st (7/day)—bless, command (DC 15), cure\n" +
				"light wounds, divine favor, endure\n" +
				"elements, magic weapon, shield of faith\n" +
				"0 (at will)—detect magic, detect poison,\n" +
				"guidance, light, mending, purify food and\n" +
				"drink, resistance, stabilize, virtue\n" +
				"Mystery winterPOTN");

		Offense offense = new Offense();

		// Act
		offense.parse(input);

		// Assert
		assertEquals("30 ft.", offense.getSpeed());
		assertEquals("mwk spear +7/+2 (1d8–1/×3)", offense.getMelee());
		assertEquals("dart +9 (1d4–1)", offense.getRanged());
		assertNull(offense.getSpace());
		assertNull(offense.getReach());
		assertNull(offense.getSpecialAttacks());
	}
}
