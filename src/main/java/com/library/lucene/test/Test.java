package com.library.lucene.test;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.CharsRef;

import java.io.IOException;

public class Test {
    // 배치 색인
    // 실시간 색인
    public static void main(String[] args) throws IOException {
        String s = "blue is the sky";

        StandardAnalyzer analyzer = new StandardAnalyzer();

        TokenStream tokenStream = analyzer.tokenStream("string", s);

        CharTermAttribute cta = tokenStream.addAttribute(CharTermAttribute.class);

        PositionIncrementAttribute pa = tokenStream.addAttribute(PositionIncrementAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken())
                System.out.println("CharTerm:  = " + cta + " | " + "PositionIncrement: " + pa.getPositionIncrement());
            tokenStream.end();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tokenStream.close();
        }

        SynonymMap.Builder builder = new SynonymMap.Builder(true);
        builder.add(new CharsRef("good"), new CharsRef("nice"), true);
        builder.add(new CharsRef("great"), new CharsRef("nice"), true);

    }

}
