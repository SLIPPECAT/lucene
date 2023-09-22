package com.library.lucene.chapter8.example;

import com.library.lucene.chapter8.CsvLoader;
import com.library.lucene.chapter8.RestaurantInfoVo;
import com.library.analyer.MyAnalyzer;
import com.library.lucene.chapter8.service.AnalyzerService;


import java.io.IOException;
import java.util.List;

public class MyAnalyzerExample {

    public static void main(String[] args) throws IOException {
        CsvLoader csvLoader = new CsvLoader();
        List<RestaurantInfoVo> restaurantInfoVoList = csvLoader.readRestaurantnfo();

        MyAnalyzer analyzer = new MyAnalyzer();

        AnalyzerService analyzerService = new AnalyzerService();
        analyzerService.analyzeText(restaurantInfoVoList, analyzer);
    }
}
