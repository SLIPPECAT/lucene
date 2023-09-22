package com.library.analyer;

import org.apache.lucene.analysis.Analyzer;

public class CustomAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        return null;
    }

}
