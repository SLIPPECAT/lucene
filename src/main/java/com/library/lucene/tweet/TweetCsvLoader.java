package com.library.lucene.tweet;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TweetCsvLoader {
    public List<TweetPost> readEnglishReview(){
        // CSV 파일의 경로를 설정한다.
        String fileName = "src/main/resources/tweet_en.csv";
        return getReview(fileName);
    }

    public List<TweetPost> readKoreanReview(){
        String fileName = "src/main/resources/tweet_ko.csv";
        return getReview(fileName);
    }

    public static List<TweetPost> getReview(String fileName){

        List<TweetPost> reviewList = null;
        try(Reader reader = Files.newBufferedReader(Paths.get(fileName))){
            CsvToBean<TweetPost> csvToBean = new CsvToBeanBuilder<TweetPost>(reader)
                    .withType(TweetPost.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            reviewList = csvToBean.parse();
        }catch (Exception e){
            e.printStackTrace();
        }
        return reviewList;
    }

}
