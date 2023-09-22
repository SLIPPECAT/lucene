package com.library.lucene.chapter8.example;

import com.library.lucene.chapter8.*;
import com.library.lucene.chapter8.analyzer.CustomKoreanAnalyzer;
import com.library.lucene.chapter8.service.IndexService;
import com.library.lucene.chapter8.service.SearchService;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.List;

public class MoreLikeThisExample {
    public static void main(String[] args) throws Exception {
        String indexPath = "/Users/ryujun-yeong/Documents/projects/common/lib/lucene/src/main/resources/index";

        CsvLoader csvHelper = new CsvLoader();
        List<RestaurantInfoVo> tourInfoList = csvHelper.readRestaurantnfo();

        Directory index = FSDirectory.open(Paths.get(indexPath));

        IndexService indexService = new IndexService();
        indexService.indexRestaurantInfo(index, tourInfoList);

        int maxHitCount = 10;

        String queryText = "박속밀국낙지탕은 꽃게장, 게꾹지, 우럭젓국찌개 등과 함께 태안의 향토음식 중 하나로서 박과 야채를 넣어 끓인 국물에 낙지를 넣어 살짝 익혀 먹은 후 그 육수에 수제비나 칼국수를 넣어 함께 먹는 음식으로 시원하고 맑은 국물과 쫄깃한 낙지의 조화가 일품이다.";

        getMoreLikeThis(index, maxHitCount, queryText);
    }

    private static void getMoreLikeThis(Directory index, int maxHitCount, String queryText) throws Exception {
        IndexReader reader  = DirectoryReader.open(index);
        MoreLikeThis moreLikeThis = new MoreLikeThis(reader);
        String[] fields = new String[1];
        fields[0] = "description";

        moreLikeThis.setFieldNames(fields);
        moreLikeThis.setAnalyzer(new CustomKoreanAnalyzer());
        Query q = moreLikeThis.like(5);
        moreLikeThis.like("description", new StringReader(queryText));

        SearchService searchService = new SearchService();
        searchService.getQueryResult(index, maxHitCount, q);
    }
}
