package com.library.lucene.chapter8.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;

public class SynonymAnalyzer extends Analyzer {
    private SynonymMap synonymMap;

    public SynonymAnalyzer(SynonymMap synonymMap) {
        this.synonymMap = synonymMap;
    }


    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new StandardTokenizer();
        SynonymGraphFilter synonymGraphFilter = null;
        try {
            synonymGraphFilter = new SynonymGraphFilter(tokenizer, synonymMap, true);
            return new TokenStreamComponents(tokenizer, synonymGraphFilter);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
