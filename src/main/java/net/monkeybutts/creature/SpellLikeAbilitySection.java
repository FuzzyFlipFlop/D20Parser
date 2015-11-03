package net.monkeybutts.creature;

import java.util.Collections;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellLikeAbilitySection  extends SpellSection {
    private static final String CONSTANT = "Constant -".toUpperCase();
    private static final String AT_WILL = "At will -".toUpperCase();
    private static final String PER_DAY_9 = "9/day -".toUpperCase();
    private static final String PER_DAY_8 = "8/day -".toUpperCase();
    private static final String PER_DAY_7 = "7/day -".toUpperCase();
    private static final String PER_DAY_6 = "6/day -".toUpperCase();
    private static final String PER_DAY_5 = "5/day -".toUpperCase();
    private static final String PER_DAY_4 = "4/day -".toUpperCase();
    private static final String PER_DAY_3 = "3/day -".toUpperCase();
    private static final String PER_DAY_2 = "2/day -".toUpperCase();
    private static final String PER_DAY_1 = "1/day -".toUpperCase();

    public void parse(String input) throws Exception {
        super.parse(input);

        input = input.replaceAll("\r\n", "\n").replaceAll("—", " - ");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(upperInput, CONSTANT);
        tokenIndexes.add(upperInput, AT_WILL);
        tokenIndexes.add(upperInput, PER_DAY_9);
        tokenIndexes.add(upperInput, PER_DAY_8);
        tokenIndexes.add(upperInput, PER_DAY_7);
        tokenIndexes.add(upperInput, PER_DAY_6);
        tokenIndexes.add(upperInput, PER_DAY_5);
        tokenIndexes.add(upperInput, PER_DAY_4);
        tokenIndexes.add(upperInput, PER_DAY_3);
        tokenIndexes.add(upperInput, PER_DAY_2);
        tokenIndexes.add(upperInput, PER_DAY_1);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if (tokenIndex.getToken().equals(CONSTANT)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.CONSTANT, 0, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(AT_WILL)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL0, 0, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_9)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL9, 9, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_8)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL8, 8, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_7)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL7, 7, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_6)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL6, 6, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_5)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL5, 5, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_4)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL4, 4, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_3)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL3, 3, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_2)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL2, 2, parseSpells(getTokenValue(input, tokenIndexes, i))));
            } else if (tokenIndex.getToken().equals(PER_DAY_1)) {
                getSpellCategories().add(new SpellCategory(SpellCategory.LEVEL1, 1, parseSpells(getTokenValue(input, tokenIndexes, i))));
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }
}
