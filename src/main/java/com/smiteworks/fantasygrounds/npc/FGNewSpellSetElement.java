package com.smiteworks.fantasygrounds.npc;

import com.smiteworks.fantasygrounds.FGBasicElement;
import net.monkeybutts.creature.*;

import java.util.List;

/**
 * Created by Stimpyjc on 10/31/2015.
 */
public class FGNewSpellSetElement extends FGBasicElement {
    private static final String REFERENCE_NAME = "spellset";
    private static final String CLASS_NAME = "spellset";
    private static final String NAME = "spellset";

    public FGNewSpellSetElement(String name, String referenceName, String className) {
        super(name, referenceName, className);
    }

    public static FGNewSpellSetElement createFrom(Creature creature) throws Exception {
        FGNewSpellSetElement spellSetElement = new FGNewSpellSetElement(NAME, REFERENCE_NAME, CLASS_NAME);

        List<SpellSection> spellSectionList = creature.getOffense().getSpellSectionList();

        for (int i = 0; i < spellSectionList.size(); i++) {
            SpellSection spellSection = spellSectionList.get(i);

            String referenceName = String.format("id-%05d", i + 1);
            spellSetElement.addElement(createFrom(spellSection, referenceName));
        }

        return spellSetElement;
    }

    private static FGBasicElement createFrom(SpellSection spellSection, String referenceName) throws Exception {
        FGBasicElement spellSectionElement = null;
        if (spellSection instanceof SpellKnownSection)
            spellSectionElement = new FGBasicElement("Spells Known", referenceName, "spellSet");
        else if (spellSection instanceof SpellLikeAbilitySection)
            spellSectionElement = new FGBasicElement("Spell-Like Abilities", referenceName, "spellSet");
        else if (spellSection instanceof SpellPreparedSection)
            spellSectionElement = new FGBasicElement("Spells Prepared", referenceName, "spellSet");

        if (spellSectionElement == null)
            throw new Exception(String.format("Unknown spellSection type '%s'", spellSection.getClass().getName()));

        spellSectionElement.addProperty("label", "string", spellSectionElement.getName())
                .addProperty("cl", "number", spellSection.getCasterLevel())
                .addProperty("availablelevel0", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL0)))
                .addProperty("availablelevel1", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL1)))
                .addProperty("availablelevel2", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL2)))
                .addProperty("availablelevel3", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL3)))
                .addProperty("availablelevel4", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL4)))
                .addProperty("availablelevel5", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL5)))
                .addProperty("availablelevel6", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL6)))
                .addProperty("availablelevel7", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL7)))
                .addProperty("availablelevel8", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL8)))
                .addProperty("availablelevel9", "number", Integer.toString(spellSection.getSpellsPerDay(SpellCategory.LEVEL9)))
                .addProperty("points", "number", "0")
                .addProperty("pointsused", "number", "0")
                .addProperty("sp", "number", "0")
                .addElement(createConcentrationCheckElement(spellSection))
                .addElement(createDifficultyClassElement(spellSection))
                .addElement(createLevelsElement(spellSection));

        return spellSectionElement;
    }

    private static FGBasicElement createLevelsElement(SpellSection spellSection) {
        return null;
    }

    private static FGBasicElement createDifficultyClassElement(SpellSection spellSection) {
        FGBasicElement element = new FGBasicElement("Difficulty Class", "dc", "difficultyClass");

        element.addProperty("ability", "string", "")
                .addProperty("abilityMod", "number", "0")
                .addProperty("misc", "number", "0")
                .addProperty("total", "number", "0");

        return element;
    }

    private static FGBasicElement createConcentrationCheckElement(SpellSection spellSection) {
        FGBasicElement element = new FGBasicElement("Concentration Check", "cc", "concentrationCheck");

        element.addProperty("misc", "number", spellSection.getConcentration());

        return element;
    }
}
