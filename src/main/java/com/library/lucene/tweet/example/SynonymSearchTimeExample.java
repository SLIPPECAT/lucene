package com.library.lucene.tweet.example;


import com.library.lucene.chapter8.analyzer.SynonymAnalyzer;
import com.library.lucene.tweet.IndexService;
import com.library.lucene.tweet.SearchService;
import com.library.lucene.tweet.TweetCsvLoader;
import com.library.lucene.tweet.TweetPost;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.CharsRef;

import java.nio.file.Path;
import java.util.List;

public class SynonymSearchTimeExample {

    public static void main(String args[]) throws Exception{
        TweetCsvLoader csvUtil= new TweetCsvLoader();
        List<TweetPost> reviewList = csvUtil.readEnglishReview();
        reviewList.forEach(System.out::println);

        // 색인한다.
        Directory index = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/algorithm/club/index/tweet"));

        // 색인 시 분석기는 기본 분석기를 사용한다.
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        // TweetPost 리스트를 색인한다.
        IndexService.indexingTweetData(reviewList, index, config);

        // 유의어 검색을 위해 SynonymMap을 생성한다.
        SynonymMap.Builder builder = new SynonymMap.Builder(true);
        builder.add(new CharsRef("nice"), new CharsRef("good"), true);

        // 쿼리를 생성할 때 SynonymAnalyzer를 사용한다.
        Query q = new QueryParser("test", new SynonymAnalyzer(builder.build())).parse("nice");

        // 쿼리로 검색하여 결과를 확인한다.
        SearchService.searchTweetData(index, q);
    }



}
