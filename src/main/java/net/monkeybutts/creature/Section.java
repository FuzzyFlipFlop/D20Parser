package net.monkeybutts.creature;

import java.util.ArrayList;

/**
 * Created by Stimpyjc on 8/29/2015.
 */
public class Section {
    protected String getTokenValue(String input, TokenIndexList tokenIndexes, int index) {
        String result;

        TokenIndex tokenIndex = tokenIndexes.get(index);
        if ((index+1) < tokenIndexes.size()) {
            result = input.substring(tokenIndex.getIndexOf() + tokenIndex.getName().length(), tokenIndexes.get(index + 1).getIndexOf()).trim();
        } else {
            result = input.substring(tokenIndex.getIndexOf() + tokenIndex.getName().length()).trim();
        }

        while (result.endsWith(";") || result.endsWith(","))
            result = result.substring(0,  result.length()-1);

        return result.trim();
    }

    protected class TokenIndex implements Comparable<TokenIndex> {
        private String token;
        private String name;
        private int indexOf;

        public TokenIndex(String token, String name, int indexOf) {
            this.token = token;
            this.name = name;
            this.indexOf = indexOf;
        }

        public String getToken() {
            return token;
        }

        public String getName() {
            return name;
        }

        public int getIndexOf() {
            return indexOf;
        }

        public int compareTo(TokenIndex o) {
            return this.getIndexOf()-o.getIndexOf();
        }
    }

    protected int indexOfLine(String input, int line) {
        int index = 0;
        for (int i = 0; i < line; i++) {
            index = input.indexOf('\n', index+1);
        }
        return index + 1;
    }

    protected class TokenIndexList extends ArrayList<TokenIndex> {
        public TokenIndexList add(String input, String token) {
            int index = indexOfToken(input, 0, token);
            if (index < 0) return this;

            String name = input.substring(index, index + token.length()).trim();

            TokenIndex tokenIndex = new TokenIndex(token, name, index);
            super.add(tokenIndex);
            return this;
        }

        public TokenIndexList addLineBegin(String input, String token) {
            int index = indexOfToken(input, 0, token);
            if (index < 0) return this;

            // backup to beginning of line
            int indexBegin = index;
            while ( indexBegin > 0) {
                char ch = input.charAt(indexBegin-1);
                if (ch == '\n') break;

                indexBegin = indexBegin - 1;
            }

            while (Character.isWhitespace(input.charAt(indexBegin))) indexBegin = indexBegin + 1;

            String name = input.substring(indexBegin, index + token.length()).trim();

            TokenIndex tokenIndex = new TokenIndex(token, name, indexBegin);
            super.add(tokenIndex);
            return this;
        }

        protected int indexOfToken(String input, int startIndex, String token) {
            int index = input.indexOf(token, startIndex);
            if (index < 0) return index;

            // Check to make sure the token is not in the middle of other text (should have whitespace on either side)
            boolean match = true;
            if (index > 0) match = Character.isWhitespace(input.charAt(index - 1));
            if (index+token.length() < input.length()-1) match &= Character.isWhitespace(input.charAt(index+token.length()));

            // If not a match, find the next occurrence.
            if (!match) return indexOfToken(input, index+token.length(), token);
            return index;
        }
    }
}
