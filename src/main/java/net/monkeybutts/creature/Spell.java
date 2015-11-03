package net.monkeybutts.creature;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mike on 11/1/15.
 */
public class Spell {
    private String name;
    private int count;
    private Integer difficultyClass;
    private String reference;

    public Spell(String name, int count, Integer difficultyClass, String reference) {
        this.name = name;
        this.count = count;
        this.difficultyClass = difficultyClass;
        this.reference = reference;
    }

    public static Spell createFromString(String input) {
        int indexInfo = input.indexOf("(");

        String spell;
        int count = 1;
        Integer difficultyClass = null;
        String reference = null;

        String info = "";
        if (indexInfo > 0) {
            spell = input.substring(0, indexInfo).trim();
            info = input.substring(indexInfo + 1, input.lastIndexOf(")"));
        } else
            spell = input;

        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(spell);

        int index = 0;
        while (matcher.find()) {
            index = matcher.start();
        }

        if (index < spell.length() - 1) {
            reference = spell.substring(index + 1);
            spell = spell.substring(0, index + 1);
        }

        String[] infoSplit = info.split(",");

        for (String part : infoSplit) {
            part = part.trim();
            if (part.matches("\\d+"))
                count = Integer.parseInt(part);
            else if (part.matches("DC\\s\\d+"))
                difficultyClass = Integer.parseInt(part.substring(2).trim());
        }

        return new Spell(spell, count, difficultyClass, reference);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public Integer getDifficultyClass() {
        return difficultyClass;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public String toString() {
        String info = "";
        if (reference != null && !reference.isEmpty())
            info = reference;

        if (count > 1) {
            if (!info.isEmpty()) info += ", ";
            info += String.format("%d", count);
        }

        if (difficultyClass != null) {
            if (!info.isEmpty()) info += ", ";
            info += String.format("DC %d", difficultyClass);
        }

        return name + ((!info.isEmpty()) ? String.format("(%s)", info) : "");
    }
}
