package net.monkeybutts.creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellSection extends Section {
    private static final String CL = "CL";
    private static final String CONCENTRATION = "CONCENTRATION";

    private String casterLevel;
    private String concentration;

    public void parse(String input) throws Exception {
        int indexStart = input.indexOf("(");
        int indexEnd = input.indexOf(")");

        // Only want caster level information
        input = input.substring(indexStart+1, indexEnd).replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(upperInput, CL);
        tokenIndexes.add(upperInput, CONCENTRATION);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if (tokenIndex.getToken().equals(CL)) {
                casterLevel = getTokenValue(input, tokenIndexes, i);
            } else if (tokenIndex.getToken().equals(CONCENTRATION)) {
                concentration = getTokenValue(input, tokenIndexes, i);
            }
        }
    }

    public String getCasterLevel() {
        return casterLevel;
    }

    public String getConcentration() {
        return concentration;
    }

    protected List<String> parseSpells(String input) {
        List<String> spells = new ArrayList<>();
        String[] split = input.replaceAll("\\s+", " ").split(",");

        String item = "";
        for (String part : split) {
            if (!item.isEmpty()) item += ",";
            item += part;

            int countLeftParenthesis = countChar(item, '(');
            int countRightParenthesis = countChar(item, ')');
            if (countLeftParenthesis == countRightParenthesis) {
                spells.add(normalizeSpell(item.trim()));
                item = "";
            }
        }

        return spells;
    }

    private int countChar(String input, char ch) {
        int count = 0;
        int index = input.indexOf(ch);
        while (index >= 0) {
            count++;
            index = input.indexOf(ch, index+1);
        }

        return count;
    }

    private String normalizeSpell(String input) {
        int indexInfo = input.indexOf("(");

        String spell;
        String info = "";
        if (indexInfo > 0) {
            spell = input.substring(0, indexInfo).trim();
            info = input.substring(indexInfo+1, input.lastIndexOf(")"));
        } else
            spell = input;

        Pattern pattern = Pattern.compile("[a-z]");
        Matcher matcher = pattern.matcher(spell);

        int index = 0;
        while(matcher.find()) {
            index = matcher.start();
        }

        if (index < spell.length()-1) {
            String ref = spell.substring(index+1);
            spell = spell.substring(0, index+1);
            if (!info.isEmpty())
                info = ref + ", " + info;
            else
                info = ref;
        }

        String retSpell = spell;
        if (!info.isEmpty())
            retSpell += " (" + info + ")";

        return retSpell;
    }
}
