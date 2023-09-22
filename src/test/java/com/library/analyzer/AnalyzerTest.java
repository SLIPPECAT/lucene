package com.library.analyzer;

import com.library.analyer.CustomAnalyzer;
import com.library.analyer.CustomKoreanAnalyzer;
import com.library.analyer.NgramAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerTest {

    @Test
    void analyzeTest() throws ParseException, IOException {

//        String text = "사랑은 늘 도망가";
        String text = "봄여름가을겨울";

//        Analyzer analyzer = new KoreanAnalyzer();
//        Analyzer analyzer = new CustomKoreanAnalyzer();
        Analyzer analyzer = new NgramAnalyzer();

        TokenStream tokenStream = analyzer.tokenStream("field", new StringReader(text));

        CharTermAttribute termAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();

        while (tokenStream.incrementToken()){
            System.out.println("형태소: " + termAttribute.toString());
        }

        tokenStream.close();
        analyzer.close();

    }
}
