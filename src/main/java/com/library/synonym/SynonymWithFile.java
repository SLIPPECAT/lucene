package com.library.synonym;

import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SynonymWithFile {

    public static void main(String[] args) throws IOException {
        // 파일을 이용한 동의어 입력
        Map<String, String> sffargs = new HashMap<>();
        sffargs.put("synonyms", "synonyms.csv");
        sffargs.put("ignoreCase", "true");

        CustomAnalyzer.Builder customAnalyzerBuilder = CustomAnalyzer.builder()
                .withTokenizer(WhitespaceTokenizerFactory.class)
                .addTokenFilter(SynonymGraphFilterFactory.class, sffargs);
        CustomAnalyzer build = customAnalyzerBuilder.build();

    }


}
