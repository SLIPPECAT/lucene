package com.library.lucene.chapter8.service;

import com.library.lucene.chapter8.RestaurantInfoVo;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.List;

public class AnalyzerService {
    public void analyzeText(List<RestaurantInfoVo> restaurantInfoList, Analyzer analyzer) throws IOException {
        System.out.println("\n\n\n###################" + analyzer.getClass().getName() + " Test Result #############################################################");
        for (RestaurantInfoVo restaurantInfoVo : restaurantInfoList){
            if(restaurantInfoVo.getDescription() != null){
                TokenStream tokenStream = analyzer.tokenStream("description", restaurantInfoVo.getDescription());
                CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
                try {
                    tokenStream.reset();
                    System.out.println();
                    while(tokenStream.incrementToken()){
                        System.out.print(charTermAttribute);
                        System.out.print(" | ");
                    }
                    tokenStream.end();
                } finally {
                    tokenStream.close();
                }
            }
        }
    }
}
