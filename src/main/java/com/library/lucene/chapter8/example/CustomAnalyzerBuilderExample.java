package com.library.lucene.chapter8.example;

import com.library.lucene.chapter8.CsvLoader;
import com.library.lucene.chapter8.RestaurantInfoVo;
import com.library.lucene.chapter8.service.AnalyzerService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;

import java.io.IOException;
import java.util.List;

public class CustomAnalyzerBuilderExample {

    public static void main(String[] args) throws IOException {
        CsvLoader csvHelper = new CsvLoader();
        List<RestaurantInfoVo> restaurantInfoVoList = csvHelper.readRestaurantnfo();

        Analyzer analyzer = CustomAnalyzer.builder()
//                .withTokenizer(KoreanTokenizerFactory.class)
//                .addTokenFilter(KoreanNumberFilterFactory.class)
//                .addTokenFilter(KoreanReadingFormFilterFactory.class)
//                .addTokenFilter(KoreanPartOfSpeechStopFilterFactory.class)
                .withTokenizer(StandardTokenizerFactory.class)
//                .withTokenizer(NGramTokenizerFactory.class)
                .addTokenFilter(LowerCaseFilterFactory.class)
                .addTokenFilter(ASCIIFoldingFilterFactory.class)
                .addTokenFilter(StopFilterFactory.class, "ignoreCase", "false")
                .build();

        AnalyzerService analyzerService = new AnalyzerService();
        analyzerService.analyzeText(restaurantInfoVoList, analyzer);
    }

}
