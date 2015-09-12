package net.monkeybutts.creature;

import java.util.Collections;

/**
 * Created by Stimpyjc on 8/26/2015.
 */
public class Header extends Section {
    // HEADER section
    private static final String NAME = "NAME";
    private static final String CR = "CR";
    private static final String XP = "XP";
    private static final String ALIGNMENT = "ALIGNMENT";
    private static final String TYPE = "TYPE";
    private static final String INITIATIVE = "Init".toUpperCase();
    private static final String SENSES = "Senses".toUpperCase();
    private static final String PERCEPTION = "Perception".toUpperCase();

    private String name;
    private String challengeRating;
    private String experience;
    private String alignment;
    private String type;
    private String initiative;
    private String senses;
    private String perception;

    public void parse(String input) throws Exception {
        input = input.replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(new TokenIndex(NAME, "", 0));
        tokenIndexes.add(upperInput, CR);
        tokenIndexes.add(upperInput, XP);
        tokenIndexes.add(new TokenIndex(ALIGNMENT, "", indexOfLine(input, 2)));
        tokenIndexes.add(new TokenIndex(TYPE, "", input.indexOf(' ',indexOfLine(input, 2))+1));
        tokenIndexes.add(upperInput, INITIATIVE);
        tokenIndexes.add(upperInput, SENSES);
        tokenIndexes.add(upperInput, PERCEPTION);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if ( tokenIndex.getToken().equals(NAME)) {
                name = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(CR)) {
                challengeRating = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(XP)) {
                experience = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(ALIGNMENT)) {
                alignment = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(TYPE)) {
                type = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(INITIATIVE)) {
                initiative = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(SENSES)) {
                senses = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(PERCEPTION)) {
                perception = getTokenValue(input, tokenIndexes, i);
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    protected int indexOfLine(String input, int line) {
        int index = 0;
        for (int i = 0; i < line; i++) {
            index = input.indexOf('\n', index+1);
        }
        return index + 1;
    }

    public String getName() {
        return name;
    }

    public String getChallengeRating() {
        return challengeRating;
    }

    public String getExperience() {
        return experience;
    }

    public String getAlignment() {
        return alignment;
    }

    public String getType() {
        return type;
    }

    public String getInitiative() {
        return initiative;
    }

    public String getSenses() {
        return senses;
    }

    public String getPerception() {
        return perception;
    }
}
