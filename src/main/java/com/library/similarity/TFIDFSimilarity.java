package com.library.similarity;

import java.util.HashMap;
import java.util.Map;

public class TFIDFSimilarity implements Similarity {

    protected Map<String, Integer> termFrequency;
    protected Map<String, Integer> documentFrequency;
    protected int totalDocuments;

    public TFIDFSimilarity() {
        this.termFrequency = new HashMap<>();
        this.documentFrequency = new HashMap<>();
        this.totalDocuments = 0;
    }

    @Override
    public double score(String word){
        return calculateTF(word) * calculateIDF(word);
    }

    public void addDocument(String[] words){
        totalDocuments++;
        for (String word: words){
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);

            if (!documentFrequency.containsKey(word)){
                documentFrequency.put(word, documentFrequency.getOrDefault(word, 0) + 1);
            }
        }
    }

    private double calculateTF(String word){
        int wordFrequency = termFrequency.getOrDefault(word, 0);
        return (double) wordFrequency / getTotalWordsInDocuments();
    }

    private double calculateIDF(String word){
        int wordDocumentFrequency = documentFrequency.getOrDefault(word, 0);
        return Math.log((double) totalDocuments / (1 + wordDocumentFrequency) + 1);
    }

    protected int getTotalWordsInDocuments(){
        int totalWords = 0;
        for (int frequency : termFrequency.values()){
            totalWords += frequency;
        }
        return totalWords;
    }


}
