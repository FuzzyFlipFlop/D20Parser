package com.smiteworks.fantasygrounds.npc;

import com.smiteworks.fantasygrounds.FGBasicElement;
import net.monkeybutts.creature.Creature;

/**
 * Created by Stimpyjc on 10/31/2015.
 */
public class FGNewNpcElement extends FGBasicElement {
    private static final String CLASS_NAME = "npcItem";

    public FGNewNpcElement(String name, String referenceName, String className) {
        super(name, referenceName, className);
    }

    public static FGNewNpcElement createFrom(Creature creature, String referenceName) throws Exception {
        FGNewNpcElement npcElement = new FGNewNpcElement(creature.getHeader().getName(), referenceName, CLASS_NAME);

        npcElement.addProperty("name", "string", creature.getHeader().getName())
                .addProperty("type", "string", creature.getHeader().getType())
                .addProperty("aura", "string", creature.getHeader().getAura())
                .addProperty("hd", "string", creature.getDefense().getHitDice())
                .addProperty("speed", "string", creature.getOffense().getSpeed())
                .addProperty("ac", "string", creature.getDefense().getArmorClass())
                .addProperty("babgrp", "string", getBaseAttackBonusGroup(creature.getStatistics().getBaseAttack(), creature.getStatistics().getCmb(), creature.getStatistics().getCmd()))
                .addProperty("atk", "string", getSingleAttack(creature.getOffense().getMelee(), creature.getOffense().getRanged()))
                .addProperty("fullatk", "string", getFullAttack(creature.getOffense().getMelee(), creature.getOffense().getRanged()))
                .addProperty("spacereach", "string", getSpaceReachGroup(creature.getOffense().getSpace(), creature.getOffense().getReach()))
                .addProperty("specialattacks", "string", creature.getOffense().getSpecialAttacks())
                .addProperty("senses", "string", creature.getHeader().getSenses())
                .addProperty("specialqualities", "string", null)
                .addProperty("skills", "string", creature.getStatistics().getSkills())
                .addProperty("feats", "string", creature.getStatistics().getFeats())
                .addProperty("environment", "string", creature.getEcology().getEnvironment())
                .addProperty("organization", "string", creature.getEcology().getOrganization())
                .addProperty("treasure", "string", creature.getEcology().getTreasure())
                .addProperty("alignment", "string", creature.getHeader().getAlignment())
                .addProperty("strength", "number", creature.getStatistics().getStrength())
                .addProperty("dexterity", "number", creature.getStatistics().getDexterity())
                .addProperty("constitution", "number", creature.getStatistics().getConstitution())
                .addProperty("intelligence", "number", creature.getStatistics().getIntelligence())
                .addProperty("wisdom", "number", creature.getStatistics().getWisdom())
                .addProperty("charisma", "number", creature.getStatistics().getCharisma())
                .addProperty("fortitudesave", "number", creature.getDefense().getFortitudeSave())
                .addProperty("reflexsave", "number", creature.getDefense().getReflexSave())
                .addProperty("willsave", "number", creature.getDefense().getWillSave())
                .addProperty("hp", "number", creature.getDefense().getHitPoints())
                .addProperty("init", "number", creature.getHeader().getInitiative())
                .addProperty("cr", "number", creature.getHeader().getChallengeRating())
                .addProperty("languages", "string", creature.getStatistics().getLanguages())
                .addProperty("xp", "number", creature.getHeader().getExperience())
                .addProperty("text", "string", null);

        if (!creature.getOffense().getSpellSectionList().isEmpty()) {
            npcElement.addElement(FGNewSpellSetElement.createFrom(creature));
        }

        return npcElement;
    }

    protected static String getBaseAttackBonusGroup(String baseAttack, String cmb, String cmd) {
        // Base Attack Bonus Group (Base Attack Bonus, CMB, CMD)
        String babgrp = "Base Atk " + baseAttack + "; ";
        babgrp += (cmb != null && !cmb.isEmpty()) ? "CMB " + cmb + "; " : "CMB 0; ";
        babgrp += (cmd != null && !cmd.isEmpty()) ? "CMD " + cmd + ";" : "CMB 10;";

        return babgrp;
    }

    protected static String getSpaceReachGroup(String space, String reach) {
        // Space / Reach
        String spacereach = (space != null && !space.isEmpty()) ? space.replaceAll("\\s+", "") + "/" : "5ft./";
        spacereach += (reach != null && !reach.isEmpty()) ? reach.replaceAll("\\s+", "") : "5ft.";

        return spacereach;
    }

    protected static String getSingleAttack(String melee, String ranged) {
        String retAttack = "";

        if (melee != null && !melee.isEmpty())
            retAttack = getSingleAttack(melee, false);

        if (ranged != null && !ranged.isEmpty()) {
            if (!retAttack.isEmpty()) retAttack += " or ";
            retAttack += getSingleAttack(ranged, true);
        }

        return retAttack;
    }

    protected static String getSingleAttack(String attack, boolean ranged) {
        String retAttack = "";

        String[] attackOptions = attack.split(" or ");
        for (String attackOption : attackOptions) {
            String firstAttack = attackOption.split(",")[0].trim();

            int indexEndBonuses = firstAttack.lastIndexOf(' ');
            int indexStartBonuses = firstAttack.lastIndexOf(' ', indexEndBonuses - 1);

            String bonuses = firstAttack.substring(indexStartBonuses, indexEndBonuses).trim();

            String[] bonusSplit = bonuses.split("/");

            if (!retAttack.isEmpty()) retAttack += " or ";
            retAttack += firstAttack.substring(0, indexStartBonuses).trim()
                    + " " + bonusSplit[0]
                    + ((ranged) ? " ranged" : "")
                    + " " + firstAttack.substring(indexEndBonuses).trim();
        }

        return retAttack.trim();
    }

    protected static String getFullAttack(String melee, String ranged) {
        String fullAttack = "";

        if (melee != null && !melee.isEmpty())
            fullAttack = getFullAttack(melee, false);

        if (ranged != null && !ranged.isEmpty()) {
            if (!fullAttack.isEmpty()) fullAttack += " or ";
            fullAttack += getFullAttack(ranged, true);
        }

        return fullAttack;
    }

    protected static String getFullAttack(String attack, boolean ranged) {
        String fullAttack = "";

        String[] attackOptions = attack.split(" or ");
        for (String attackOption : attackOptions) {
            if (!fullAttack.isEmpty()) fullAttack += " or ";

            String[] attacks = attackOption.split(",");
            String buffer = "";
            for (String attackItem : attacks) {
                if (!buffer.isEmpty()) buffer += ", ";

                int indexEndBonuses = attackItem.lastIndexOf(' ');

                buffer += attackItem.substring(0, indexEndBonuses).trim()
                        + ((ranged) ? " ranged" : "")
                        + " " + attackItem.substring(indexEndBonuses).trim();
            }

            fullAttack += buffer.trim();
        }

        return fullAttack.trim();
    }
}
