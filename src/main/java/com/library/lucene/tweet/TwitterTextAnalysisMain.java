package com.library.lucene.tweet;

import com.library.util.AnalyzerService;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.core.StopAnalyzer;

import java.util.List;

public class    TwitterTextAnalysisMain {
    public static void main(String args[]) throws Exception{
        // CSV 파일에서 데이터를 읽는다.
        TweetCsvLoader csvUtil = new TweetCsvLoader();
        List<TweetPost> reviewList = csvUtil.readEnglishReview();

        // 분석기 서비스를 생성한다.
        AnalyzerService analyzerService = new AnalyzerService();

        // StandardAnalyzer를 테스트한다.
//        analyzerService.analyzeText(reviewList, new StandardAnalyzer());

        // SimpleAnalyzer를 테스트한다.
//        analyzerService.analyzeText(reviewList, new SimpleAnalyzer());
//
//        // WhitespaceAnalyzer를 테스트한다.
//        analyzerService.analyzeText(reviewList, new WhitespaceAnalyzer());
        CharArraySet set = new CharArraySet(10, true);
        set.add("trump");
        set.add("must");
        set.add("sunshine");
        set.add("the");



//        // StopAnalyzer를 테스트한다.
//        analyzerService.analyzeText(reviewList, new StopAnalyzer());
        analyzerService.analyzeText(reviewList, new StopAnalyzer(set));
//
//        // KeywordAnalyzer를 테스트한다.
//        analyzerService.analyzeText(reviewList, new KeywordAnalyzer());

    }
}
