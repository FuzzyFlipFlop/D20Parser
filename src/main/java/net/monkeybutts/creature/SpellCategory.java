package net.monkeybutts.creature;

import java.util.List;

/**
 * Created by mike on 10/31/15.
 */

public class SpellCategory {
    public static final String CONSTANT = "CONSTANT";
    public static final String LEVEL0 = "LEVEL0";
    public static final String LEVEL1 = "LEVEL1";
    public static final String LEVEL2 = "LEVEL2";
    public static final String LEVEL3 = "LEVEL3";
    public static final String LEVEL4 = "LEVEL4";
    public static final String LEVEL5 = "LEVEL5";
    public static final String LEVEL6 = "LEVEL6";
    public static final String LEVEL7 = "LEVEL7";
    public static final String LEVEL8 = "LEVEL8";
    public static final String LEVEL9 = "LEVEL9";

    private String code;
    private int count;
    private List<Spell> spells;

    public SpellCategory(String code, int count, List<Spell> spells) {
        this.code = code;
        this.count = count;
        this.spells = spells;
    }

    public String getCode() {
        return code;
    }

    public int getCount() {
        return count;
    }

    public List<Spell> getSpells() {
        return spells;
    }
}
