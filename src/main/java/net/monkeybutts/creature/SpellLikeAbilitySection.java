package net.monkeybutts.creature;

import java.util.Collections;
import java.util.List;

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

    private List<String> constantSpells;
    private List<String> atWillSpells;
    private List<String> perDay9Spells;
    private List<String> perDay8Spells;
    private List<String> perDay7Spells;
    private List<String> perDay6Spells;
    private List<String> perDay5Spells;
    private List<String> perDay4Spells;
    private List<String> perDay3Spells;
    private List<String> perDay2Spells;
    private List<String> perDay1Spells;

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
                constantSpells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(AT_WILL)) {
                atWillSpells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_9)) {
                perDay9Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_8)) {
                perDay8Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_7)) {
                perDay7Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_6)) {
                perDay6Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_5)) {
                perDay5Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_4)) {
                perDay4Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_3)) {
                perDay3Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_2)) {
                perDay2Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(PER_DAY_1)) {
                perDay1Spells = parseSpells(getTokenValue(input, tokenIndexes, i));
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    public List<String> getConstantSpells() {
        return constantSpells;
    }

    public List<String> getAtWillSpells() {
        return atWillSpells;
    }

    public List<String> getPerDay9Spells() {
        return perDay9Spells;
    }

    public List<String> getPerDay8Spells() {
        return perDay8Spells;
    }

    public List<String> getPerDay7Spells() {
        return perDay7Spells;
    }

    public List<String> getPerDay6Spells() {
        return perDay6Spells;
    }

    public List<String> getPerDay5Spells() {
        return perDay5Spells;
    }

    public List<String> getPerDay4Spells() {
        return perDay4Spells;
    }

    public List<String> getPerDay3Spells() {
        return perDay3Spells;
    }

    public List<String> getPerDay2Spells() {
        return perDay2Spells;
    }

    public List<String> getPerDay1Spells() {
        return perDay1Spells;
    }
}
