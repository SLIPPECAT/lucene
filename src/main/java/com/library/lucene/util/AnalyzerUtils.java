package com.library.lucene.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;
import java.io.StringReader;

public class AnalyzerUtils {

    public static void displayTokensWithFullDetails(Analyzer analyzer, String text) throws IOException {

        TokenStream stream = analyzer.tokenStream("contents", new StringReader(text));

        CharTermAttribute term = stream.addAttribute(CharTermAttribute.class);
        PositionIncrementAttribute posIncr =
                stream.addAttribute(PositionIncrementAttribute.class);
        OffsetAttribute offset = stream.addAttribute(OffsetAttribute.class);
        TypeAttribute type = stream.addAttribute(TypeAttribute.class);

        int position = 0;

        stream.reset();

        while (stream.incrementToken()) {


            int increment = posIncr.getPositionIncrement();
            if (increment > 0){
                position = position + increment;
                System.out.println();
                System.out.println(position + ": ");
            }
            System.out.print("[" +                                 // #E
                    term.chars() + ":" +                   // #E
                    offset.startOffset() + "->" +         // #E
                    offset.endOffset() + ":" +            // #E
                    type.type() + "] ");                  // #E
        }
        System.out.println();
        }

    public static void main(String[] args) throws IOException {
        System.out.println("WhitespaceAnalyzer");
        displayTokensWithFullDetails(new WhitespaceAnalyzer(),
                "The quick brown fox....");

        System.out.println("\n----");
        System.out.println("StandardAnalyzer");
        displayTokensWithFullDetails(new StandardAnalyzer(),
                "I'll email you at xyz@example.com");
    }

}
