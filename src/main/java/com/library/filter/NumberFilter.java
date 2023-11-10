package com.library.filter;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import java.io.IOException;

public class NumberFilter extends TokenFilter {

    private CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
    private PositionIncrementAttribute positionIncrementAttribute = addAttribute(PositionIncrementAttribute.class);

    /**
     * Construct a token stream filtering the given input.
     *
     * @param input
     */
    protected NumberFilter(TokenStream input) {
        super(input);
    }

    @Override
    public boolean incrementToken() throws IOException {
        while (input.incrementToken()){
            char[] buffer = charTermAttribute.buffer();
            int length = charTermAttribute.length();
            // 토큰이 숫자인지 확인하여 숫자를 제거
            if (!containsNumber(buffer, length)){
                return true;
            };
        }
        return false;
    }

    private boolean containsNumber(char[] buffer, int length) {
        for (int i = 0; i < length; i++) {
            if (Character.isDigit(buffer[i])){
                return true;
            }
        }
        return false;
    }
}
