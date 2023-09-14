package com.library.executor;

import com.library.model.PopSong;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

public class SearchTest {

    public static void main(String[] args) throws ParseException, IOException {
//        String text = "queen car";
        String text = "uptown girl";
//        String text = "chandelier";
        Searcher searcher = new Searcher();
        List<PopSong> results = searcher.search(text);
//        results.forEach(r -> System.out.println(r.toString()));
    }
}
