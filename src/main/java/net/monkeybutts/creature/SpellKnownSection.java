package net.monkeybutts.creature;

import java.util.Collections;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellKnownSection extends SpellSection {
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
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL9, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_8)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL8, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_7)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL7, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_6)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL6, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_5)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL5, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_4)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL4, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_3)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL3, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_2)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL2, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_1)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL1, parseSpellsPerDay(tokenIndex.getValue()), parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(LEVEL_0)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL0, 0, parseSpells(getTokenValue(input, tokenIndexes, i))));
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    protected int parseSpellsPerDay(String input) {
        int indexStart = input.indexOf("(") + 1;
        int indexEnd = input.indexOf("/");
        if (indexEnd < 0) indexEnd = input.indexOf(")");

        return Integer.parseInt(input.substring(indexStart, indexEnd));
    }
}
