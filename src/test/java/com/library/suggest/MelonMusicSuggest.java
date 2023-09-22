package com.library.suggest;

import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.fst.FSTCompletionLookup;
import org.apache.lucene.search.suggest.fst.WFSTCompletionLookup;
import org.apache.lucene.search.suggest.tst.TSTLookup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class MelonMusicSuggest {

    @Test
    void test() throws IOException {
        String input = "봄 여름 가을 겨울";
        Lookup lookup = new TSTLookup();
//        Lookup lookup = new WFSTCompletionLookup();
//        Lookup lookup = new FSTCompletionLookup();
        List<Lookup.LookupResult> lookupResults = lookup.lookup(input, false, 3);
        for (Lookup.LookupResult result : lookupResults){
            System.out.println(input);
            System.out.println("--> " + result.key + "(" + result.value + ")");
        }
    }

}
