package com.library.lucene.tweet.example;


import com.library.analyer.SynonymAnalyzer;
import com.library.synonym.dto.SynonymRequestDto;
import com.library.lucene.tweet.IndexService;
import com.library.lucene.tweet.SearchService;
import com.library.lucene.tweet.TweetCsvLoader;
import com.library.lucene.tweet.TweetPost;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.CharsRef;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SynonymIndexTimeExample {

    public static void main(String[] args) {

        TweetCsvLoader csvUtil = new TweetCsvLoader();
        List<TweetPost> reviewList = csvUtil.readEnglishReview();

        try {
            Directory index = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/algorithm/club/index/tweet"));

            SynonymMap.Builder builder = new SynonymMap.Builder(true);
            builder.add(new CharsRef("good"), new CharsRef("nice"), true);



            Analyzer analyzer = new SynonymAnalyzer(builder.build());
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexService.indexingTweetData(reviewList, index, config);

            Query q = new QueryParser("test", new StandardAnalyzer()).parse("nice");
            SearchService.searchTweetData(index, q);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
//    private void relateSynonym(String target, String synonym){
//        SynonymService synonymService = new SynonymService();
//        synonymService.relateSynonym(new SynonymRequestDto(target, synonym));
//    }

}
