package com.library.lucene.chapter8.example;

import com.library.lucene.chapter8.CsvLoader;
import com.library.lucene.chapter8.RestaurantInfoVo;
import com.library.lucene.chapter8.analyzer.CustomLengthAnalyzer;
import com.library.lucene.chapter8.service.AnalyzerService;
import com.library.lucene.tweet.IndexService;

import java.io.IOException;
import java.util.List;

public class CustomLengthAnalyzerExample {
    public static void main(String[] args) throws IOException {
        CsvLoader csvLoader = new CsvLoader();
        List<RestaurantInfoVo> restaurantInfoVoList = csvLoader.readRestaurantnfo();

        CustomLengthAnalyzer analyzer = new CustomLengthAnalyzer();
        AnalyzerService analyzerService = new AnalyzerService();

        analyzerService.analyzeText(restaurantInfoVoList, analyzer);
    }
}
