package net.monkeybutts.creature;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class Creature extends Section {
    private static final String HEADER = "HEADER";
    private static final String DEFENSE = "DEFENSE";
    private static final String OFFENSE = "OFFENSE";
	private static final String STATISTICS = "STATISTICS";
	private static final String TACTICS = "TACTICS";
	private static final String SPECIAL_ABILITIES = "SPECIAL ABILITIES";
	private static final String ECOLOGY = "ECOLOGY";

    private Header header;
    private Defense defense;
    private Offense offense;
    private Statistics statistics;
    private Tactics tactics;
    private SpecialAbilities specialAbilities;
    private Ecology ecology;

    public void parseFile(String fileName) throws Exception {
        Charset encoding = Charset.forName("UTF-8");
        byte[] encoded = Files.readAllBytes(Paths.get(fileName));
		String content = new String(encoded, encoding);
        parse(content);
    }

    public void parse(String input) throws Exception {
        input = input.replaceAll("\r\n", "\n");
        String upperInput = input.toUpperCase();

        TokenIndexList tokenIndexes = new TokenIndexList();
        tokenIndexes.add(new TokenIndex(HEADER, "", 0));
        tokenIndexes.add(upperInput, DEFENSE);
        tokenIndexes.add(upperInput, OFFENSE);
        tokenIndexes.add(upperInput, STATISTICS);
        tokenIndexes.add(upperInput, TACTICS);
        tokenIndexes.add(upperInput, SPECIAL_ABILITIES);
        tokenIndexes.add(upperInput, ECOLOGY);

        Collections.sort(tokenIndexes);

        for (int i = 0; i < tokenIndexes.size(); i++) {
            TokenIndex tokenIndex = tokenIndexes.get(i);

            if (tokenIndex.getToken().equals(HEADER)) {
                header = new Header();
                header.parse(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(DEFENSE)) {
                defense = new Defense();
                defense.parse(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(OFFENSE)) {
                offense = new Offense();
                offense.parse(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(STATISTICS)) {
                statistics = new Statistics();
                statistics.parse(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(TACTICS)) {
                tactics = new Tactics();
                tactics.parse(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(SPECIAL_ABILITIES)) {
                specialAbilities = new SpecialAbilities();
                specialAbilities.parse(getTokenValue(input, tokenIndexes, i));
            } else if (tokenIndex.getToken().equals(ECOLOGY)) {
                ecology = new Ecology();
                ecology.parse(getTokenValue(input, tokenIndexes, i));
            }
        }
    }

    public Header getHeader() {
        return header;
    }

    public Defense getDefense() {
        return defense;
    }

    public Offense getOffense() {
        return offense;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Tactics getTactics() {
        return tactics;
    }

    public SpecialAbilities getSpecialAbilities() {
        return specialAbilities;
    }

    public Ecology getEcology() {
        return ecology;
    }
}
