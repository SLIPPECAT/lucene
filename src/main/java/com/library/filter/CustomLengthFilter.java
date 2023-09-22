package com.library.filter;

import org.apache.lucene.analysis.FilteringTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

public class CustomLengthFilter extends FilteringTokenFilter {

    private final int min;
    private final int max;

    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class) ;

    public CustomLengthFilter(TokenStream in, int min, int max) {
        super(in);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean accept() {
        final int len = termAttribute.length();
        return (len >= min && len  <= max);
    }
}
