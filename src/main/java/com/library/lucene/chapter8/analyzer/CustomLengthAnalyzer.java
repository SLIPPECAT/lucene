package com.library.lucene.chapter8.analyzer;

import com.library.lucene.chapter8.filter.CustomLengthFilter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;

public class CustomLengthAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new WhitespaceTokenizer();
        TokenStream result = new CustomLengthFilter(source, 5, Integer.MAX_VALUE);
        return new TokenStreamComponents(source, result);
    }
}
