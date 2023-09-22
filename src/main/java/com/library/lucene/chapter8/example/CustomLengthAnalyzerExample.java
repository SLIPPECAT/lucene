package com.library.lucene.chapter8.example;

import com.library.lucene.chapter8.CsvLoader;
import com.library.lucene.chapter8.RestaurantInfoVo;
import com.library.analyer.CustomLengthAnalyzer;
import com.library.lucene.chapter8.service.AnalyzerService;

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
