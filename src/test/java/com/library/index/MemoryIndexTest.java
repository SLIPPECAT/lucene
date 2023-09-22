package com.library.index;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.memory.MemoryIndex;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.junit.jupiter.api.Test;

public class MemoryIndexTest {

    @Test
    void test() throws ParseException {
        Analyzer analyzer = new SimpleAnalyzer();
        MemoryIndex index = new MemoryIndex();
        index.addField("content", "Readings about Salmons and other select Alaska fishing Manuals", analyzer);
        index.addField("author", "Tales of James", analyzer);

        QueryParser parser = new QueryParser("content", analyzer);
        float score = index.search(parser.parse("+author:james +salmon~ +fish* manual~"));
        System.out.println("score = " + score);
        if (score > 0.0f) {
            System.out.println("it's a match");
        } else {
            System.out.println("no match found");
        }
        System.out.println("indexData=" + index.toString());

    }

}
