package com.library.application.analysis.kiwi;

import com.library.config.FilePathConfig;
import org.apache.lucene.analysis.Analyzer;

public class KiwiAnalyzer extends Analyzer {

    private final FilePathConfig filePathConfig;
    private final KiwiTokenizer kiwiTokenizer;
    public KiwiAnalyzer(FilePathConfig filePathConfig) throws Exception {
        super();
        this.filePathConfig = filePathConfig;
        kiwiTokenizer = new KiwiTokenizer(filePathConfig);
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        return new Analyzer.TokenStreamComponents(kiwiTokenizer);
    }

}
