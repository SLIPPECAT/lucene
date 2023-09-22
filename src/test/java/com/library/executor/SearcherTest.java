package com.library.executor;

import com.library.model.PopSong;
import com.library.similarity.CustomBM25Similarity;
import com.library.similarity.CustomSimilarity;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {


    Analyzer analyzer = new WhitespaceAnalyzer();
    Path indexPath = Path.of("/Users/ryujun-yeong/Documents/projects/common/lib/lucene/index");


    @BeforeEach
    void setUp() throws IOException {

    }

    @Test
    void test_search() throws IOException, ParseException {
        String text = "uptown girl";

        Directory index = FSDirectory.open(indexPath);
        QueryParser lyricsQueryParser = new QueryParser("lyrics", analyzer);
        Query lyricQuery = lyricsQueryParser.parse(text);

        QueryParser songQueryParser = new QueryParser("song", analyzer);
        Query songQuery = songQueryParser.parse(text);
        // BoostQuery를 이용해서 가중치 조절
        songQuery = new BoostQuery(songQuery, 0.5f);

        BooleanQuery.Builder booleanQueryBuilder = new BooleanQuery.Builder();
        booleanQueryBuilder.add(new BooleanClause(lyricQuery, BooleanClause.Occur.SHOULD));
        booleanQueryBuilder.add(new BooleanClause(songQuery, BooleanClause.Occur.SHOULD));

        Query finalQuery = booleanQueryBuilder.build();

        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);

//        CustomBM25Similarity customBM25Similarity = new CustomBM25Similarity();
        Similarity similarity = new BM25Similarity(2, 0.9F);
        searcher.setSimilarity(similarity);

        // 결국 Search를 할 때 스코어링이 되는 것이
        ScoreDoc[] hits = searcher.search(finalQuery, 10).scoreDocs;
        StoredFields storedFields = searcher.storedFields();

        List<PopSong> popSongs = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = storedFields.document(hits[i].doc);
            String song = hitDoc.get("song");
            String lyrics = hitDoc.get("lyrics");
            popSongs.add(new PopSong(song, lyrics));
            Explanation explanation  =
                    searcher.explain(finalQuery, hits[i].doc);
            System.out.println("----------------------");
            System.out.println("song = " + song);
            System.out.println("explanation = " + explanation.toString());
        }
        reader.close();
        index.close();
    }

}