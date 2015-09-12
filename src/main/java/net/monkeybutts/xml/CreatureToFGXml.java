package net.monkeybutts.xml;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import net.monkeybutts.creature.Creature;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by Stimpyjc on 8/30/2015.
 */
public class CreatureToFGXml {
    public static Document createDocument() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

        return builder.newDocument();
    }

    public static Element createFromCreature(Document doc, Creature creature) {
        Element element = doc.createElement(creature.getHeader().getName().replaceAll("\\s+", ""));
        doc.appendChild(element);

        addLibraryLinkElement(element, "windowreference", "npc", "..");

        // Hit Points
        String[] hpsplit = creature.getDefense().getHitPoints().split(" ");

        // Attack
        String attack = "";

        addElement(element, "name", "string", creature.getHeader().getName());
        addElement(element, "type", "string", creature.getHeader().getType());
        addElement(element, "aura", "string", null);
        addElement(element, "hd", "string", hpsplit[1].substring(1, hpsplit[1].length() - 1));
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
        addElement(element, "environment", "string", null);
        addElement(element, "organization", "string", null);
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
        addElement(element, "hp", "number", hpsplit[0]);
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

    protected static Element addElement(Element parent, String name, String type, String body) {
        if (body == null || body.isEmpty()) return null;

        Document doc = parent.getOwnerDocument();
        Element childElement = doc.createElement(name);
        parent.appendChild(childElement);

        childElement.setAttribute("type", type);
        childElement.appendChild(doc.createTextNode(body));

        return childElement;
    }

    protected static Element addLibraryLinkElement(Element parent, String type, String className, String recordName) {
        Document doc = parent.getOwnerDocument();
        Element childElement = doc.createElement("librarylink");
        parent.appendChild(childElement);

        childElement.setAttribute("type", type);

        Element classElement = doc.createElement("class");
        childElement.appendChild(classElement);
        classElement.appendChild(doc.createTextNode(className));

        Element recordNameElement = doc.createElement("recordname");
        childElement.appendChild(recordNameElement);
        recordNameElement.appendChild(doc.createTextNode(recordName));

        return childElement;
    }

    public static final void prettyPrint(Document xml) throws Exception {
        OutputFormat format = new OutputFormat(xml);
        format.setLineWidth(65);
        format.setIndenting(true);
        format.setIndent(2);
        Writer out = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(out, format);
        serializer.serialize(xml);
        System.out.println(out.toString());
    }
}
