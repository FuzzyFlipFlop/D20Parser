package net.monkeybutts.creature;

import java.util.Collections;
import java.util.List;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellKnownSection  extends SpellSection {
    private static final String PER_DAY_REG = "\\((\\d+|\\d+/DAY)\\)";
    private static final String LEVEL_9 = "9TH " + PER_DAY_REG + " -";
    private static final String LEVEL_8 = "8TH " + PER_DAY_REG + " -";
    private static final String LEVEL_7 = "7TH " + PER_DAY_REG + " -";
    private static final String LEVEL_6 = "6TH " + PER_DAY_REG + " -";
    private static final String LEVEL_5 = "5TH " + PER_DAY_REG + " -";
    private static final String LEVEL_4 = "4TH " + PER_DAY_REG + " -";
    private static final String LEVEL_3 = "3RD " + PER_DAY_REG + " -";
    private static final String LEVEL_2 = "2ND " + PER_DAY_REG + " -";
    private static final String LEVEL_1 = "1ST " + PER_DAY_REG + " -";
    private static final String LEVEL_0 = "0 \\(AT WILL\\) -";

    private int level9SpellsPerDay;
    private List<String> level9Spells;
    private int level8SpellsPerDay;
    private List<String> level8Spells;
    private int level7SpellsPerDay;
    private List<String> level7Spells;
    private int level6SpellsPerDay;
    private List<String> level6Spells;
    private int level5SpellsPerDay;
    private List<String> level5Spells;
    private int level4SpellsPerDay;
    private List<String> level4Spells;
    private int level3SpellsPerDay;
    private List<String> level3Spells;
    private int level2SpellsPerDay;
    private List<String> level2Spells;
    private int level1SpellsPerDay;
    private List<String> level1Spells;
    private List<String> level0Spells;

    public void parse(String input) throws Exception {
        super.parse(input);

        input = input.replaceAll("\r\n", "\n").replaceAll("—", " - ");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(upperInput, LEVEL_9);
        tokenIndexes.add(upperInput, LEVEL_8);
        tokenIndexes.add(upperInput, LEVEL_7);
        tokenIndexes.add(upperInput, LEVEL_6);
        tokenIndexes.add(upperInput, LEVEL_5);
        tokenIndexes.add(upperInput, LEVEL_4);
        tokenIndexes.add(upperInput, LEVEL_3);
        tokenIndexes.add(upperInput, LEVEL_2);
        tokenIndexes.add(upperInput, LEVEL_1);
        tokenIndexes.add(upperInput, LEVEL_0);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if (tokenIndex.getToken().equals(LEVEL_9)) {
                level9SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level9Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_8)) {
                level8SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level8Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_7)) {
                level7SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level7Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_6)) {
                level6SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level6Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_5)) {
                level5SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level5Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_4)) {
                level4SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level4Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_3)) {
                level3SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level3Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_2)) {
                level2SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level2Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_1)) {
                level1SpellsPerDay = parseSpellsPerDay(tokenIndex.getValue());
                level1Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_0)) {
                level0Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    protected int parseSpellsPerDay(String input) {
        int indexStart = input.indexOf("(")+1;
        int indexEnd = input.indexOf("/");
        if (indexEnd < 0) indexEnd = input.indexOf(")");

        return Integer.parseInt(input.substring(indexStart, indexEnd));
    }

    public int getLevel9SpellsPerDay() {
        return level9SpellsPerDay;
    }

    public List<String> getLevel9Spells() {
        return level9Spells;
    }

    public int getLevel8SpellsPerDay() {
        return level8SpellsPerDay;
    }

    public List<String> getLevel8Spells() {
        return level8Spells;
    }

    public int getLevel7SpellsPerDay() {
        return level7SpellsPerDay;
    }

    public List<String> getLevel7Spells() {
        return level7Spells;
    }

    public int getLevel6SpellsPerDay() {
        return level6SpellsPerDay;
    }

    public List<String> getLevel6Spells() {
        return level6Spells;
    }

    public int getLevel5SpellsPerDay() {
        return level5SpellsPerDay;
    }

    public List<String> getLevel5Spells() {
        return level5Spells;
    }

    public int getLevel4SpellsPerDay() {
        return level4SpellsPerDay;
    }

    public List<String> getLevel4Spells() {
        return level4Spells;
    }

    public int getLevel3SpellsPerDay() {
        return level3SpellsPerDay;
    }

    public List<String> getLevel3Spells() {
        return level3Spells;
    }

    public int getLevel2SpellsPerDay() {
        return level2SpellsPerDay;
    }

    public List<String> getLevel2Spells() {
        return level2Spells;
    }

    public int getLevel1SpellsPerDay() {
        return level1SpellsPerDay;
    }

    public List<String> getLevel1Spells() {
        return level1Spells;
    }

    public List<String> getLevel0Spells() {
        return level0Spells;
    }
}
