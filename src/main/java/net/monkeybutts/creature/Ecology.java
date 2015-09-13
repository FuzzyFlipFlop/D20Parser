package net.monkeybutts.creature;

import java.util.Collections;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class Ecology extends Section {
    // ECOLOGY section
    private static final String ENVIRONMENT = "ENVIRONMENT";
    private static final String ORGANIZATION = "ORGANIZATION";
    private static final String TREASURE = "TREASURE";
    private static final String TEXT = "TEXT";

    private String environment;
    private String organization;
    private String treasure;
    private String text;

    public void parse(String input) {
        input = input.replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(upperInput, ENVIRONMENT);
        tokenIndexes.add(upperInput, ORGANIZATION);
        tokenIndexes.add(upperInput, TREASURE);
        tokenIndexes.add(new TokenIndex(TEXT, "", indexOfLine(input, 3)));

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if ( tokenIndex.getToken().equals(ENVIRONMENT)) {
                environment = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(ORGANIZATION)) {
                organization = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(TREASURE)) {
                treasure = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(TEXT)) {
                text = getTokenValue(input, tokenIndexes, i).trim();
            }
        }
    }

    public String getEnvironment() {
        return environment;
    }

    public String getOrganization() {
        return organization;
    }

    public String getTreasure() {
        return treasure;
    }

    public String getText() {
        return text;
    }
}
