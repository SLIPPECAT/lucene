package com.library.scenario.chapter8.example;

import com.library.scenario.chapter8.CsvLoader;
import com.library.scenario.chapter8.RestaurantInfoVo;
import com.library.analyer.CustomLengthAnalyzer;
import com.library.scenario.chapter8.service.AnalyzerService;

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
