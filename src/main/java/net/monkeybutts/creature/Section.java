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

    protected TokenIndex indexOfToken(String input, String tokenValue) {
        return indexOfToken(input, 0, tokenValue, false);
    }

    protected TokenIndex indexOfToken(String input, String tokenValue, boolean emptyToken) {
        return indexOfToken(input, 0, tokenValue, emptyToken);
    }

    protected TokenIndex indexOfToken(String input, int startIndex, String tokenValue, boolean emptyToken) {
        return indexOfToken(input, startIndex, tokenValue, tokenValue, emptyToken);
    }

    protected TokenIndex indexOfToken(String input, String tokenName, String[] tokenValues) {
        return indexOfToken(input, 0, tokenName, tokenValues, false);
    }

    protected TokenIndex indexOfToken(String input, String tokenName, String[] tokenValues, boolean emptyToken) {
        return indexOfToken(input, 0, tokenName, tokenValues, emptyToken);
    }

    protected TokenIndex indexOfToken(String input, int startIndex, String tokenName, String[] tokenValues, boolean emptyToken) {
        TokenIndex retIndex = null;

        for (String tokenValue : tokenValues) {
            TokenIndex index = indexOfToken(input, startIndex, tokenName, tokenValue, emptyToken);
            if ( (retIndex == null) || (index != null && index.getIndexStart() < retIndex.getIndexStart()) )
                retIndex = index;
        }

        return retIndex;
    }

    protected TokenIndex indexOf(String input, int startIndex, String tokenValue) {
        return indexOf(input, startIndex, tokenValue, tokenValue, false);
    }

    protected TokenIndex indexOf(String input, int startIndex, String tokenValue, boolean emptyToken) {
        return indexOf(input, startIndex, tokenValue, tokenValue, emptyToken);
    }

    protected TokenIndex indexOf(String input, int startIndex, String tokenName, String tokenValue, boolean emptyToken) {
        Pattern pattern = Pattern.compile(tokenValue);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find(startIndex)) {
            int indexStart = matcher.start();
            int indexEnd = matcher.end();

            if (!emptyToken)
                return new TokenIndex(tokenName, matcher.group(), indexStart, indexEnd);
            else
                return new TokenIndex(tokenName, "", indexStart);
        }

        return null;
    }

    protected TokenIndex indexOfToken(String input, int startIndex, String tokenName, String tokenValue, boolean emptyToken) {
        TokenIndex tokenIndex = indexOf(input, startIndex, tokenName, tokenValue, emptyToken);
        if (tokenIndex == null)
            return null;

        if (!emptyToken) {
            // Check to make sure the token is not in the middle of other text (should have whitespace on either side)
            int indexStart = tokenIndex.getIndexStart();
            int indexEnd = tokenIndex.getIndexEnd();
            boolean match = true;
            if (indexStart > 0) match = Character.isWhitespace(input.charAt(indexStart - 1));
            if (indexEnd < input.length() - 1) match &= Character.isWhitespace(input.charAt(indexEnd));

            // If not a match, find the next occurrence.
            if (!match) tokenIndex = indexOfToken(input, indexEnd, tokenName, tokenValue, emptyToken);
        }

        return tokenIndex;
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
            return add(input, token, false);
        }

        public TokenIndexList add(String input, String token, boolean emptyToken) {
            TokenIndex index = indexOfToken(input, token, emptyToken);
            if (index == null) return this;

            super.add(index);
            return this;
        }

        public TokenIndexList add(String input, String tokenName, String[] tokenValues) {
            return add(input, tokenName, tokenValues, false);
        }

        public TokenIndexList add(String input, String tokenName, String[] tokenValues, boolean emptyToken) {
            TokenIndex index = indexOfToken(input, tokenName, tokenValues, emptyToken);
            if (index == null) return this;

            super.add(index);
            return this;
        }

        public TokenIndexList addLineBegin(String input, String token) {
            return addLineBegin(input, token, false);
        }

        public TokenIndexList addLineBegin(String input, String token, boolean emptyToken) {
            TokenIndex index = indexOfToken(input, token, emptyToken);
            if (index == null) return this;

            // backup to beginning of line
            int indexBegin = indexOfLineBegin(input, index.getIndexStart());

            String value = input.substring(indexBegin, index.getIndexEnd()).trim();

            TokenIndex tokenIndex = new TokenIndex(token, value, indexBegin, index.getIndexEnd());
            super.add(tokenIndex);
            return this;
        }

        public TokenIndexList addLineBegin(String input, String tokenName, String[] tokenValues) {
            return addLineBegin(input, tokenName, tokenValues, false);
        }

        public TokenIndexList addLineBegin(String input, String tokenName, String[] tokenValues, boolean emptyToken) {
            TokenIndex index = indexOfToken(input, tokenName, tokenValues, emptyToken);
            if (index == null) return this;

            // backup to beginning of line
            int indexBegin = indexOfLineBegin(input, index.getIndexStart());

            TokenIndex tokenIndex;
            if (!emptyToken) {
                String value = input.substring(indexBegin, index.getIndexEnd()).trim();
                tokenIndex = new TokenIndex(tokenName, value, indexBegin, index.getIndexEnd());
            } else
                tokenIndex = new TokenIndex(tokenName, "", indexBegin);

            super.add(tokenIndex);
            return this;
        }
    }
}
