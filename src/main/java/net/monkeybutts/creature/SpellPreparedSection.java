package net.monkeybutts.creature;

import java.util.Collections;
import java.util.List;

/**
 * Created by Stimpyjc on 10/11/2015.
 */
public class SpellPreparedSection extends SpellSection  {
    private static final String LEVEL_9 = "9TH -";
    private static final String LEVEL_8 = "8TH -";
    private static final String LEVEL_7 = "7TH -";
    private static final String LEVEL_6 = "6TH -";
    private static final String LEVEL_5 = "5TH -";
    private static final String LEVEL_4 = "4TH -";
    private static final String LEVEL_3 = "3RD -";
    private static final String LEVEL_2 = "2ND -";
    private static final String LEVEL_1 = "1ST -";
    private static final String LEVEL_0 = "0 \\(AT WILL\\) -";
    private static final String SPECIALIZATION = "SPECIALIZATION";
    private static final String[] SPECIALIZATION_TOKENS = {"SPECIALIZATION", "SCHOOL", "OPPOSITION", "PROHIBITED",
            "ABJURATION", "CONJURATION", "DIVINATION", "ENCHANTMENT", "EVOCATION", "ILLUSION", "NECROMANCY",
            "TRANSMUTATION", "UNIVERSALIST", "DOMAIN"};

    private String specialization;

    public SpellPreparedSection() {
    }


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

        // Look for specialization section
        TokenIndex index = indexOfToken(upperInput.toUpperCase(), SPECIALIZATION_TOKENS);
        if (index != null) {
            tokenIndexes.add(new TokenIndex(SPECIALIZATION, "", indexOfLineBegin(upperInput, index.getIndexStart())));
        }

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if (tokenIndex.getToken().equals(LEVEL_9)) {
                addSpellCategory(SpellCategory.LEVEL9, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_8)) {
                addSpellCategory(SpellCategory.LEVEL8, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_7)) {
                addSpellCategory(SpellCategory.LEVEL7, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_6)) {
                addSpellCategory(SpellCategory.LEVEL6, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_5)) {
                addSpellCategory(SpellCategory.LEVEL5, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_4)) {
                addSpellCategory(SpellCategory.LEVEL4, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_3)) {
                addSpellCategory(SpellCategory.LEVEL3, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_2)) {
                addSpellCategory(SpellCategory.LEVEL2, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_1)) {
                addSpellCategory(SpellCategory.LEVEL1, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(LEVEL_0)) {
                addSpellCategory(SpellCategory.LEVEL0, parseSpells(getTokenValue(input, tokenIndexes, i)));
            } else if (tokenIndex.getToken().equals(SPECIALIZATION)) {
                specialization = getTokenValue(input, tokenIndexes, i);
            }
        }
    }

    private void addSpellCategory(String code, List<Spell> spells) {
        int count = 0;
        for (Spell spell : spells) {
            count += spell.getCount();
        }
        super.getSpellCategories().add(new SpellCategory(code, count, spells));
    }


    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }


    public String getSpecialization() {
        return specialization;
    }
}
