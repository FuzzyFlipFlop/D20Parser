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

    private List<String> level9Spells;
    private List<String> level8Spells;
    private List<String> level7Spells;
    private List<String> level6Spells;
    private List<String> level5Spells;
    private List<String> level4Spells;
    private List<String> level3Spells;
    private List<String> level2Spells;
    private List<String> level1Spells;
    private List<String> level0Spells;
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
                level9Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_8)) {
                level8Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_7)) {
                level7Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_6)) {
                level6Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_5)) {
                level5Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_4)) {
                level4Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_3)) {
                level3Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_2)) {
                level2Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_1)) {
                level1Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(LEVEL_0)) {
                level0Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(SPECIALIZATION)) {
                specialization = getTokenValue(input, tokenIndexes, i);
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    public List<String> getLevel9Spells() {
        return level9Spells;
    }

    public List<String> getLevel8Spells() {
        return level8Spells;
    }

    public List<String> getLevel7Spells() {
        return level7Spells;
    }

    public List<String> getLevel6Spells() {
        return level6Spells;
    }

    public List<String> getLevel5Spells() {
        return level5Spells;
    }

    public List<String> getLevel4Spells() {
        return level4Spells;
    }

    public List<String> getLevel3Spells() {
        return level3Spells;
    }

    public List<String> getLevel2Spells() {
        return level2Spells;
    }

    public List<String> getLevel1Spells() {
        return level1Spells;
    }

    public List<String> getLevel0Spells() {
        return level0Spells;
    }

    public String getSpecialization() {
        return specialization;
    }
}
