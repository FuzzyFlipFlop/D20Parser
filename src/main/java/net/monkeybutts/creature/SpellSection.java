package net.monkeybutts.creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Stimpyjc on 10/23/2015.
 */
public class SpellSection extends Section {
    private static final String CL = "CL";
    private static final String CONCENTRATION = "CONCENTRATION";

    private String casterLevel;
    private String concentration;

    private String ability;
    private String abilityModifier;

    private List<SpellCategory> spellCategories;

    public SpellSection() {
        spellCategories = new ArrayList<>();
    }

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

    public List<SpellCategory> getSpellCategories() {
        return spellCategories;
    }

    public SpellCategory getSpellCategoryByCode(String code) {
        Optional<SpellCategory> optional = spellCategories.stream().filter(x -> x.getCode().equals(code)).findFirst();
        if (optional.isPresent())
            return optional.get();

        return null;
    }

    public List<String> getSpellList(String code) throws Exception {
        SpellCategory spellCategory = getSpellCategoryByCode(code);
        if (spellCategory == null)
            throw new Exception(String.format("SpellCategory identified by '%s' was not found.", code));

        return spellCategory.getSpells().stream().map(Spell::toString).collect(Collectors.toList());
    }

    public int getSpellsPerDay(String code) throws Exception {
        SpellCategory spellCategory = getSpellCategoryByCode(code);
        if (spellCategory == null)
            throw new Exception(String.format("SpellCategory identified by '%s' was not found.", code));
        return spellCategory.getCount();
    }

    protected List<Spell> parseSpells(String input) {
        List<Spell> spells = new ArrayList<>();
        String[] split = input.replaceAll("\\s+", " ").split(",");

        String item = "";
        for (String part : split) {
            if (!item.isEmpty()) item += ",";
            item += part;

            int countLeftParenthesis = countChar(item, '(');
            int countRightParenthesis = countChar(item, ')');
            if (countLeftParenthesis == countRightParenthesis) {
                spells.add(Spell.createFromString(item.trim()));
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
}
