package com.library.service.melonMusic;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class SearchService {

    @Test
    void searchTitle() throws Exception{
        int hitsPerPage = 10;
        Directory index = FSDirectory.open(Paths.get("src/test/resources/MusicTop50/index"));

        QueryParser queryParser = new QueryParser("title", new NgramAnalyzer());
        Query query = queryParser.parse("봄");

        try(IndexReader reader = DirectoryReader.open(index)){
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(query, hitsPerPage);
            ScoreDoc[] hits = docs.scoreDocs;
            System.out.println(hits.length + " 개의 결과를 찾았습니다.");
            StoredFields storedFields = searcher.storedFields();
            for(int i=0; i < hits.length; i++) {
                Document hitDoc = storedFields.document(hits[i].doc);
                String title = hitDoc.get("title");
                String artist = hitDoc.get("artist");
                System.out.println("title = " + title);
                System.out.println("artist = " + artist);
                Explanation explanation =
                        searcher.explain(query, hits[i].doc);
                System.out.println("explanation = " + explanation);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        index.close();
    }


}
