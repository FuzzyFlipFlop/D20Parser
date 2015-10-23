package net.monkeybutts.creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Stimpyjc on 8/26/2015.
 */
public class Offense extends Section {
    // OFFENSE section
    private static final String SPEED = "Speed".toUpperCase();
    private static final String MELEE = "Melee".toUpperCase();
    private static final String RANGED = "Ranged".toUpperCase();
    private static final String SPACE = "Space".toUpperCase();
    private static final String REACH = "Reach".toUpperCase();
    private static final String SPECIAL_ATTACKS = "Special Attacks".toUpperCase();
    private static final String SPELLS0 = "Spell-Like Abilities".toUpperCase();
    private static final String SPELLS1 = "Spells Known".toUpperCase();
    private static final String SPELLS2 = "Spells Prepared".toUpperCase();

    private String speed;
    private String melee;
    private String ranged;
    private String space;
    private String reach;
    private String specialAttacks;
    private List<Section> spellSectionList;

    public Offense() {
        spellSectionList = new ArrayList<>();
    }

    public void parse(String input) throws Exception {
        input = input.replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(upperInput, SPEED);
        tokenIndexes.add(upperInput, MELEE);
        tokenIndexes.add(upperInput, RANGED);
        tokenIndexes.add(upperInput, SPACE);
        tokenIndexes.add(upperInput, REACH);
        tokenIndexes.add(upperInput, SPECIAL_ATTACKS);
        tokenIndexes.addLineBegin(upperInput, SPELLS0);
        tokenIndexes.addLineBegin(upperInput, SPELLS1);
        tokenIndexes.addLineBegin(upperInput, SPELLS2);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if ( tokenIndex.getToken().equals(SPEED)) {
                speed = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(MELEE)) {
                melee = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(RANGED)) {
                ranged = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(SPACE)) {
                space = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(REACH)) {
                reach = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(SPECIAL_ATTACKS)) {
                specialAttacks = getTokenValue(input, tokenIndexes, i);
            } else if ( tokenIndex.getToken().equals(SPELLS0)) {
                SpellLikeAbilitySection section = new SpellLikeAbilitySection();
                section.parse(getTokenValue(input, tokenIndexes, i));
                spellSectionList.add(section);
            } else if ( tokenIndex.getToken().equals(SPELLS1)) {
                SpellKnownSection section = new SpellKnownSection();
                section.parse(getTokenValue(input, tokenIndexes, i));
                spellSectionList.add(section);
            } else if ( tokenIndex.getToken().equals(SPELLS2)) {
                SpellPreparedSection section = new SpellPreparedSection();
                section.parse(getTokenValue(input, tokenIndexes, i));
                spellSectionList.add(section);
            }
        }
    }

    @Override
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        return super.getTokenValue(input, tokenIndexes, index).replaceAll("\\s+", " ").trim();
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getMelee() {
        return melee;
    }

    public void setMelee(String melee) {
        this.melee = melee;
    }

    public String getRanged() {
        return ranged;
    }

    public void setRanged(String ranged) {
        this.ranged = ranged;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getSpecialAttacks() {
        return specialAttacks;
    }

    public void setSpecialAttacks(String specialAttacks) {
        this.specialAttacks = specialAttacks;
    }
}
