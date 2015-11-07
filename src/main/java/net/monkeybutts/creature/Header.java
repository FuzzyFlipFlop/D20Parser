package net.monkeybutts.creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stimpyjc on 8/26/2015.
 */
public class Header extends Section {
    // HEADER section
    private static final String NAME = "NAME";
    private static final String CR = "CR";
    private static final String XP = "XP";
    private static final String RCL = "RCL";
    private static final String ALIGNMENT = "ALIGNMENT";
    private static final String TYPE = "TYPE";
    private static final String INITIATIVE = "Init".toUpperCase();
    private static final String SENSES = "Senses".toUpperCase();
    private static final String PERCEPTION = "Perception".toUpperCase();
    private static final String AURA = "Aura".toUpperCase();
    private static final String[] ALIGNMENTS = {"N", "NG", "NE", "LN", "LG", "LE", "CN", "CG", "CE"};

    private static final String CLASS_LEVEL = "CLASS_LEVEL";
    private static final String[] CLASSES = {"BARBARIAN", "BARD", "CLERIC", "DRUID", "FIGHTER", "MONK", "PALADIN",
            "RANGER", "ROGUE", "SORCERER", "WIZARD"};

    private String name;
    private String challengeRating;
    private String experience;
    private String raceClassLevel;
    private List<String> classes;
    private String alignment;
    private String type;
    private String initiative;
    private String senses;
    private String perception;
    private String aura;

    public void parse(String input) throws Exception {
        input = input.replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(new TokenIndex(NAME, "", 0));
        tokenIndexes.add(upperInput, CR);
        TokenIndex xpToken = indexOfToken(upperInput, XP);
        tokenIndexes.add(xpToken);
        TokenIndex token = indexOf(upperInput, xpToken.getIndexEnd() + 1, "\\s+");
        TokenIndex rclToken = new TokenIndex(RCL, "", token.getIndexEnd());
        TokenIndex alignmentToken = indexOfToken(upperInput, ALIGNMENT, ALIGNMENTS, false);
        if (rclToken.getIndexStart() == alignmentToken.getIndexStart())
            tokenIndexes.add(alignmentToken);
        else {
            tokenIndexes.add(rclToken);
            tokenIndexes.add(alignmentToken);
        }
        tokenIndexes.add(new TokenIndex(TYPE, "", alignmentToken.getIndexEnd() + 1));
        tokenIndexes.add(upperInput, INITIATIVE);
        tokenIndexes.add(upperInput, SENSES);
        tokenIndexes.add(upperInput, PERCEPTION);
        tokenIndexes.add(upperInput, AURA);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if ( tokenIndex.getToken().equals(NAME)) {
                name = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(CR)) {
                challengeRating = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(XP)) {
                experience = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(RCL)) {
                raceClassLevel = getTokenValue(input, tokenIndexes, i);
                classes = parseClasses(raceClassLevel);
            } else if ( tokenIndex.getToken().equals(ALIGNMENT)) {
                alignment = tokenIndex.getValue();
            } else if ( tokenIndex.getToken().equals(TYPE)) {
                type = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(INITIATIVE)) {
                initiative = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(SENSES)) {
                senses = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(PERCEPTION)) {
                perception = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(AURA)) {
                aura = getTokenValue(input, tokenIndexes, i);
            }
        }
    }

    protected List<String> parseClasses(String input) throws Exception {
        String upperInput = input.replaceAll("[A-Z]", "").toUpperCase();

        String[] split = upperInput.split("/");

        List<String> list = new ArrayList<>();

        for (String item : split) {
            TokenIndex tokenIndex = indexOfToken(item, CLASS_LEVEL, CLASSES, false);

            if (tokenIndex != null)
                list.add(tokenIndex.getValue());
        }

        return list;
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
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

    public List<String> getClasses() {
        return classes;
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

    public String getAura() {
        return aura;
    }
}
