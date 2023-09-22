package com.library.analyer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.ngram.NGramTokenizer;

public class NgramAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new NGramTokenizer(2, 2);
//        Tokenizer tokenizer = new StandardTokenizer();
        TokenStream filter = new NGramTokenFilter(tokenizer, 2);
        filter = new LowerCaseFilter(filter);
        return new TokenStreamComponents(tokenizer, filter);
    }
}
