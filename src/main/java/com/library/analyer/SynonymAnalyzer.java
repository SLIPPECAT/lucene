package com.library.analyer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.CharsRef;

import java.io.IOException;

public class SynonymAnalyzer extends Analyzer {

    private SynonymMap synonymMap;

    public SynonymAnalyzer(SynonymMap synonymMap) {
        this.synonymMap = synonymMap;
    }


    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer tokenizer = new NGramTokenizer();
        SynonymGraphFilter synonymGraphFilter = null;
        try {
            synonymGraphFilter = new SynonymGraphFilter(tokenizer, synonymMap, true);
            return new TokenStreamComponents(tokenizer, synonymGraphFilter);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        SynonymMap.Builder builder = new SynonymMap.Builder(true);
        builder.add(new CharsRef("good"), new CharsRef("nice"), true);
        builder.add(new CharsRef("great"), new CharsRef("nice"), true);
        Analyzer analyzer = new SynonymAnalyzer(builder.build());
    }
}
