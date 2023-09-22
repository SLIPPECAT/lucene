package com.library.lucene.tweet.example;

import com.library.analyer.NgramAnalyzer;
import com.library.lucene.tweet.IndexService;
import com.library.lucene.tweet.SearchService;
import com.library.lucene.tweet.TweetCsvLoader;
import com.library.lucene.tweet.TweetPost;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class NgramExample {

    public static void main(String[] args) {
        TweetCsvLoader csvUtil = new TweetCsvLoader();
        List<TweetPost> reviewList = csvUtil.readKoreanReview();

        try {
            Directory index = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/algorithm/club/index/tweet"));

            IndexWriterConfig config = new IndexWriterConfig(new NgramAnalyzer());
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

            IndexService.indexingTweetData(reviewList, index, config);

            Query q = new QueryParser("test", new StandardAnalyzer()).parse("먼지");

            SearchService.searchTweetData(index, q);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


}
