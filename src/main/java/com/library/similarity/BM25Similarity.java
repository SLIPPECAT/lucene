package com.library.similarity;

public class BM25Similarity extends TFIDFSimilarity{

    @Override
    public double score(String word){
        int wordFrequency = termFrequency.getOrDefault(word, 0);
        int wordDocumentFrequency = documentFrequency.getOrDefault(word, 0);

        double k1 = 1.5;
        double b = 0.75;

        double avgdl = (double) getTotalWordsInDocuments() / totalDocuments;

        double tf = (wordFrequency * (k1 + 1)) / (wordFrequency + k1 * (1 - b + b * getTotalWordsInDocuments() / avgdl));
        double idf = Math.log((totalDocuments - wordDocumentFrequency + 0.5) / (wordDocumentFrequency + 0.5));

        return tf * idf;
    }

}
