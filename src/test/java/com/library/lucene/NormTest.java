package com.library.lucene;

import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.junit.jupiter.api.Test;

public class NormTest {

    @Test
    void test(){
        Similarity similarity = new BM25Similarity();

    }
}
