package com.smiteworks.fantasygrounds.npc;

import com.smiteworks.fantasygrounds.FGElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Created by Stimpyjc on 8/30/2015.
 */
public class FGNpcRootElement extends FGElement {
    private static final String REFERENCE_NAME = "npc";
    private static final String CLASS_NAME = "npc";
    private String referenceName;
    private String name;
    private List<FGNpcElement> npcDetailElementList;

    public FGNpcRootElement(Document document, String name, String referenceName, List<FGNpcElement> npcDetailElementList) {
        super(document);

        this.name = name;
        this.referenceName = referenceName;
        this.npcDetailElementList = npcDetailElementList;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public String getReferenceName() {
        return REFERENCE_NAME;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Element createElement() {
        Element element = super.createElement();

        element.appendChild(createCategoryElement());

        return element;
    }

    protected Element createCategoryElement() {
        Element element = getDocument().createElement("category");

        element.setAttribute("name", "");
        element.setAttribute("mergeid", "");
        element.setAttribute("baseicon", "0");
        element.setAttribute("decalicon", "0");

        for (FGNpcElement detail : npcDetailElementList) {
            element.appendChild(detail.createElement());
        }

        return element;
    }



    /*
    public static Element createFromCreature(Document doc, Creature creature) {
        Element element = doc.createElement(creature.getHeader().getName().replaceAll("\\s+", ""));
        doc.appendChild(element);

        addLibraryLinkElement(element, "windowreference", "npc", "..");

        addElement(element, "name", "string", creature.getHeader().getName());
        addElement(element, "type", "string", creature.getHeader().getType());
        addElement(element, "aura", "string", creature.getHeader().getAura());
        addElement(element, "hd", "string", creature.getDefense().getHitDice());
        addElement(element, "speed", "string", creature.getOffense().getSpeed());
        addElement(element, "ac", "string", creature.getDefense().getArmorClass());
        addElement(element, "babgrp", "string", getBaseAttackBonusGroup(creature.getStatistics().getBaseAttack(), creature.getStatistics().getCmb(), creature.getStatistics().getCmd()));
        addElement(element, "atk", "string", getSingleAttack(creature.getOffense().getMelee(), creature.getOffense().getRanged()));
        addElement(element, "fullatk", "string", getFullAttack(creature.getOffense().getMelee(), creature.getOffense().getRanged()));
        addElement(element, "spacereach", "string", getSpaceReachGroup(creature.getOffense().getSpace(), creature.getOffense().getReach()));
        addElement(element, "specialattacks", "string", creature.getOffense().getSpecialAttacks());
        addElement(element, "senses", "string", creature.getHeader().getSenses());
        addElement(element, "specialqualities", "string", null);
        addElement(element, "skills", "string", creature.getStatistics().getSkills());
        addElement(element, "feats", "string", creature.getStatistics().getFeats());
        addElement(element, "environment", "string", creature.getEcology().getEnvironment());
        addElement(element, "organization", "string", creature.getEcology().getOrganization());
        addElement(element, "treasure", "string", creature.getEcology().getTreasure());
        addElement(element, "alignment", "string", creature.getHeader().getAlignment());
        addElement(element, "strength", "number", creature.getStatistics().getStrength());
        addElement(element, "dexterity", "number", creature.getStatistics().getDexterity());
        addElement(element, "constitution", "number", creature.getStatistics().getConstitution());
        addElement(element, "intelligence", "number", creature.getStatistics().getIntelligence());
        addElement(element, "wisdom", "number", creature.getStatistics().getWisdom());
        addElement(element, "charisma", "number", creature.getStatistics().getCharisma());
        addElement(element, "fortitudesave", "number", creature.getDefense().getFortitudeSave());
        addElement(element, "reflexsave", "number", creature.getDefense().getReflexSave());
        addElement(element, "willsave", "number", creature.getDefense().getWillSave());
        addElement(element, "hp", "number", creature.getDefense().getHitPoints());
        addElement(element, "init", "number", creature.getHeader().getInitiative());
        addElement(element, "cr", "number", creature.getHeader().getChallengeRating());
        addElement(element, "languages", "string", creature.getStatistics().getLanguages());
        addElement(element, "xp", "number", creature.getHeader().getExperience());
        addElement(element, "text", "string", null);

        return element;
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

    public Element createElement(Document document) {
        return null;
    }
    */
}
