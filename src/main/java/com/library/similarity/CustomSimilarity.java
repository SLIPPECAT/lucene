package com.library.similarity;

import org.apache.lucene.search.similarities.BasicStats;
import org.apache.lucene.search.similarities.SimilarityBase;

public class CustomSimilarity extends SimilarityBase {

    private double termFreqMultiplier;
    private double b;
    public CustomSimilarity() {

    }

    public CustomSimilarity(double customFreq, double b) {
        this.b = b;
        this.termFreqMultiplier = customFreq;
    }

    @Override
    protected double score(BasicStats stats, double freq, double docLen) {
        double avgFieldLength = stats.getAvgFieldLength();

        // tf에 계수 적용
        double modifiedFreq = freq * termFreqMultiplier;

//        double boost = stats.getBoost();
//        double docFreq = stats.getDocFreq();
//        double totalDocs = stats.getNumberOfDocuments();
//        double totalTermFreq = stats.getTotalTermFreq();
//        double avgFieldLength1 = stats.getAvgFieldLength();

        // TF-IDF 기본 계산식
        // freq * (log(tatalDocs / docFreq) + 1.0)
//        return termFreqMultiplier * freq * (Math.log(totalDocs / docFreq) + 1.0);
        return  modifiedFreq / ((1.0 - b) + b * (docLen / avgFieldLength));
    }

    @Override
    public String toString() {
        return "CustomSimilarity{" +
                "termFreqMultiplier=" + termFreqMultiplier +
                '}';
    }
}
