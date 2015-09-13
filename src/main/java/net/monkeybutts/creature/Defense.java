package net.monkeybutts.creature;

import java.util.Collections;

/**
 * Created by Stimpyjc on 8/26/2015.
 */
public class Defense extends Section {
    // DEFENSE section
    private static final String AC = "AC";
    private static final String HP = "HP";
    private static final String FORT = "FORT";
    private static final String REF = "REF";
    private static final String WILL = "WILL";
    private static final String DR = "DR";
    private static final String IMMUNE = "IMMUNE";
    private static final String WEAKNESSES = "WEAKNESSES";

    private String armorClass;
    private String hitPoints;
    private String hitDice;

    private String fortitudeSave;
    private String reflexSave;
    private String willSave;
    private String damageReduction;
    private String immune;
    private String weaknesses;

    public void parse(String input) throws Exception {
        input = input.replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(upperInput, AC);
        tokenIndexes.add(upperInput, HP);
        tokenIndexes.add(upperInput, FORT);
        tokenIndexes.add(upperInput, REF);
        tokenIndexes.add(upperInput, WILL);
        tokenIndexes.add(upperInput, DR);
        tokenIndexes.add(upperInput, IMMUNE);
        tokenIndexes.add(upperInput, WEAKNESSES);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if ( tokenIndex.getToken().equals(AC)) {
                armorClass = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(HP)) {
                String[] hpSplit = getTokenValue(input, tokenIndexes, i).split(" ");
                hitPoints = hpSplit[0];
                hitDice = hpSplit[1].substring(1, hpSplit[1].length() - 1);
            } else if ( tokenIndex.getToken().equals(FORT)) {
                fortitudeSave = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(REF)) {
                reflexSave = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(WILL)) {
                willSave = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(DR)) {
                damageReduction = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(IMMUNE)) {
                immune = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(WEAKNESSES)) {
                weaknesses = getTokenValue(input, tokenIndexes, i);
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    public String getArmorClass() {
        return armorClass;
    }

    public String getHitPoints() {
        return hitPoints;
    }

    public String getHitDice() {
        return hitDice;
    }

    public String getFortitudeSave() {
        return fortitudeSave;
    }

    public String getReflexSave() {
        return reflexSave;
    }

    public String getWillSave() {
        return willSave;
    }

    public String getDamageReduction() {
        return damageReduction;
    }

    public String getImmune() {
        return immune;
    }

    public String getWeaknesses() {
        return weaknesses;
    }
}
