package net.monkeybutts.creature;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class Ecology extends Section {
    private String text;

    public void parse(String input) {
        input = input.replaceAll("\r\n", "\n");

        text = input;
    }

    public String getText() {
        return text;
    }
}
