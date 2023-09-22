package com.library.similarity;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class CustomSimilarity extends SimilarityBase {
    @Override
    protected double score(BasicStats stats, double freq, double docLen) {
        double boost = stats.getBoost();
        double avgFieldLength = stats.getAvgFieldLength();
        long docFreq = stats.getDocFreq();
        long totalTermFreq = stats.getTotalTermFreq();
        double avgFieldLength1 = stats.getAvgFieldLength();

        return 0;
    }

    @Override
    public String toString() {

        return null;
    }
}
