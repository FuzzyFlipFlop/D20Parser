package net.monkeybutts.creature;

import java.util.Collections;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class Statistics extends Section {
    // STATISTICS section
    private static final String STR = "Str".toUpperCase();
    private static final String DEX = "Dex".toUpperCase();
    private static final String CON = "Con".toUpperCase();
    private static final String INT = "Int".toUpperCase();
    private static final String WIS = "Wis".toUpperCase();
    private static final String CHA = "Cha".toUpperCase();
    private static final String BASE_ATK = "Base Atk".toUpperCase();
    private static final String CMB = "CMB";
    private static final String CMD = "CMD";
    private static final String FEATS = "Feats".toUpperCase();
    private static final String SKILLS = "Skills".toUpperCase();
    private static final String LANGUAGES = "Languages".toUpperCase();
    private static final String SQ = "SQ";
    private static final String GEAR = "Gear".toUpperCase();

    private String strength;
    private String dexterity;
    private String constitution;
    private String intelligence;
    private String wisdom;
    private String charisma;
    private String baseAttack;
    private String cmb;
    private String cmd;
    private String feats;
    private String skills;
    private String languages;
    private String sq;
    private String gear;

    public void parse(String input) {
        input = input.replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(upperInput, STR);
        tokenIndexes.add(upperInput, DEX);
        tokenIndexes.add(upperInput, CON);
        tokenIndexes.add(upperInput, INT);
        tokenIndexes.add(upperInput, WIS);
        tokenIndexes.add(upperInput, CHA);
        tokenIndexes.add(upperInput, BASE_ATK);
        tokenIndexes.add(upperInput, CMB);
        tokenIndexes.add(upperInput, CMD);
        tokenIndexes.add(upperInput, FEATS);
        tokenIndexes.add(upperInput, SKILLS);
        tokenIndexes.add(upperInput, LANGUAGES);
        tokenIndexes.add(upperInput, SQ);
        tokenIndexes.addLineBegin(upperInput, GEAR);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if (tokenIndex.getToken().equals(STR)) {
                strength = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(DEX)) {
                dexterity = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(CON)) {
                constitution = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(INT)) {
                intelligence = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(WIS)) {
                wisdom = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(WIS)) {
                wisdom = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(CHA)) {
                charisma = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(BASE_ATK)) {
                baseAttack = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(CMB)) {
                cmb = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(CMD)) {
                cmd = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(FEATS)) {
                feats = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(SKILLS)) {
                skills = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(LANGUAGES)) {
                languages = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(SQ)) {
                sq = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(GEAR)) {
                gear = getTokenValue(input, tokenIndexes, i);
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    public String getStrength() {
        return strength;
    }

    public String getDexterity() {
        return dexterity;
    }

    public String getConstitution() {
        return constitution;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getWisdom() {
        return wisdom;
    }

    public String getCharisma() {
        return charisma;
    }

    public String getBaseAttack() {
        return baseAttack;
    }

    public String getCmb() {
        return cmb;
    }

    public String getCmd() {
        return cmd;
    }

    public String getFeats() {
        return feats;
    }

    public String getSkills() {
        return skills;
    }

    public String getLanguages() {
        return languages;
    }

    public String getSq() {
        return sq;
    }

    public String getGear() {
        return gear;
    }
}
