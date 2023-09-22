package com.library.lucene;

import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Test3 {

    @Test
    void test_weight(){

        Query query = new TermQuery(new Term("text", "하이용;"));



        Weight weight = new Weight(query) {
            @Override
            public Explanation explain(LeafReaderContext context, int doc) throws IOException {
                return null;
            }

            @Override
            public Scorer scorer(LeafReaderContext context) throws IOException {
                return null;
            }

            @Override
            public boolean isCacheable(LeafReaderContext ctx) {
                return false;
            }
        };

        Scorer scorer = new Scorer(weight   ) {
            @Override
            public DocIdSetIterator iterator() {
                return null;
            }

            @Override
            public float getMaxScore(int upTo) throws IOException {
                return 0;
            }

            @Override
            public float score() throws IOException {
                return 0;
            }

            @Override
            public int docID() {
                return 0;
            }
        };

        Similarity similarity = new Similarity() {
            @Override
            public long computeNorm(FieldInvertState state) {
                return 0;
            }

            @Override
            public SimScorer scorer(float boost, CollectionStatistics collectionStats, TermStatistics... termStats) {
                return null;
            }
        };


//        System.out.println(weight.getQuery().createWeight());
    }

}
