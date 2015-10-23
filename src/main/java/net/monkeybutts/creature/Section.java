package net.monkeybutts.creature;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class Section {
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        String result;

        TokenIndex tokenIndex = tokenIndexes.get(index);
        if ((index+1) < tokenIndexes.size()) {
            result = input.substring(tokenIndex.getIndexStart() + tokenIndex.getValue().length(), tokenIndexes.get(index + 1).getIndexStart()).trim();
        } else {
            result = input.substring(tokenIndex.getIndexStart() + tokenIndex.getValue().length()).trim();
        }

        while (result.endsWith(";") || result.endsWith(","))
            result = result.substring(0,  result.length()-1);

        return result.trim();
    }

    protected int indexOfLine(String input, int line) {
        int index = 0;
        for (int i = 0; i < line; i++) {
            index = input.indexOf('\n', index+1);
        }
        return index + 1;
    }

    protected TokenIndex indexOfToken(String input, String token) {
        return indexOfToken(input, 0, token);
    }

    protected TokenIndex indexOfToken(String input, int startIndex, String token) {
        Pattern pattern = Pattern.compile(token);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find(startIndex)){
            int indexStart = matcher.start();
            int indexEnd = matcher.end();
            // Check to make sure the token is not in the middle of other text (should have whitespace on either side)
            boolean match = true;
            if (indexStart > 0) match = Character.isWhitespace(input.charAt(indexStart - 1));
            if (indexEnd < input.length()-1) match &= Character.isWhitespace(input.charAt(indexEnd));

            // If not a match, find the next occurrence.
            if (!match) return indexOfToken(input, indexEnd, token);
            return new TokenIndex(token, matcher.group(), indexStart, indexEnd);
        }

        return null;
    }

    protected TokenIndex indexOfToken(String input, String[] tokens) {
        return indexOfToken(input, 0, tokens);
    }

    protected TokenIndex indexOfToken(String input, int startIndex, String[] tokens) {
        TokenIndex retIndex = null;

        for (String token : tokens) {
            TokenIndex index = indexOfToken(input, startIndex, token);
            if ( (retIndex == null) || (index != null && index.getIndexStart() < retIndex.getIndexStart()) )
                retIndex = index;
        }

        return retIndex;
    }

    protected int indexOfLineBegin(String input, int index) {
        // backup to beginning of line
        int indexBegin = index;
        while ( indexBegin > 0) {
            char ch = input.charAt(indexBegin-1);
            if (ch == '\n') break;

            indexBegin = indexBegin - 1;
        }

        while (Character.isWhitespace(input.charAt(indexBegin))) indexBegin = indexBegin + 1;

        return indexBegin;
    }

    protected class TokenIndex implements Comparable<TokenIndex> {
        private String token;
        private String value;
        private int indexStart;
        private int indexEnd;

        public TokenIndex(String token, String value, int indexStart) {
            this.token = token;
            this.value = value;
            this.indexStart = indexStart;
            this.indexEnd = indexStart + value.length();
        }

        public TokenIndex(String token, String value, int indexStart, int indexEnd) {
            this.token = token;
            this.value = value;
            this.indexStart = indexStart;
            this.indexEnd = indexEnd;
        }

        public String getToken() {
            return token;
        }

        public String getValue() {
            return value;
        }

        public int getIndexStart() {
            return indexStart;
        }

        public int getIndexEnd() {
            return indexEnd;
        }

        public int compareTo(TokenIndex o) {
            return this.getIndexStart()-o.getIndexStart();
        }
    }

    protected class TokenIndexList extends ArrayList<TokenIndex> {
        public TokenIndexList add(String input, String token) {
            TokenIndex index = indexOfToken(input, 0, token);
            if (index == null) return this;

            super.add(index);
            return this;
        }

        public TokenIndexList addLineBegin(String input, String token) {
            TokenIndex index = indexOfToken(input, 0, token);
            if (index == null) return this;

            // backup to beginning of line
            int indexBegin = indexOfLineBegin(input, index.getIndexStart());

            String value = input.substring(indexBegin, index.getIndexEnd()).trim();

            TokenIndex tokenIndex = new TokenIndex(token, value, indexBegin, index.getIndexEnd());
            super.add(tokenIndex);
            return this;
        }
    }
}
