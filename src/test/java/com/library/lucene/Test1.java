package com.library.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queries.function.FunctionScoreQuery;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.xml.builders.BoostingTermBuilder;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    @Test
    @DisplayName("MultiSearcher 테스트")
    void test_function_query(){
        Document document = new Document();
        document.add(new Field("score", "42", TextField.TYPE_NOT_STORED));

        String[] animalNames = {
                "Lion", "Tiger", "Leopard", "Cheetah", "Giraffe", "Elephant", "Kangaroo", "Koala", "Panda", "Penguin",
                "Hippopotamus", "Zebra", "Rhino", "Gorilla", "Dolphin", "Whale", "Shark", "Crocodile", "Alligator", "Polar Bear",
                "Camel", "Ostrich", "Llama", "Hedgehog", "Kangaroo", "Sloth", "Peacock", "Puma", "Jaguar"
        };






    }


    @Test
    @DisplayName("searchAfter 테스트")
    void test() throws IOException, ParseException {
        Directory index = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/projects/common/lib/lucene/index/test1"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(index, config);
        addDocument(writer, "1", "Lucene is a full-text search library.");
        addDocument(writer, "2", "It is used for information retrieval.");
        addDocument(writer, "3", "Lucene is powerful.");
        addDocument(writer, "4", "It is open-source.");
        addDocument(writer, "5", "Lucene supports complex queries.");
        writer.close();;

        QueryParser queryParser = new QueryParser("text", analyzer);
        Query query = queryParser.parse("Lucene");
        System.out.println("query.toString() = " + query.toString());

        List<Document> searchResults = search(index, query, 2);

        for (Document doc : searchResults){
            System.out.println("doc = " + doc.get("docId"));
            System.out.println(doc.get("text"));
        }

    }

    private void addDocument(IndexWriter writer, String docId, String text) throws IOException {
        Document document = new Document();
        document.add(new Field("docId", docId, TextField.TYPE_STORED));
        document.add(new Field("text", text, TextField.TYPE_STORED));
        writer.addDocument(document);
    }

    private List<Document> search(Directory index, Query query, int hit) throws IOException {
        List<Document> result = new ArrayList<>();
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));

        ScoreDoc lastDoc = null;
        TopDocs topDocs;
        while(true) {
            if (lastDoc == null) {
                topDocs = searcher.search(query, hit);
            } else {
                topDocs = searcher.searchAfter(lastDoc, query, hit);
            }

            if (topDocs.totalHits.value == 0) {
                break;
            }

            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                result.add(doc);
            }

            lastDoc = topDocs.scoreDocs[topDocs.scoreDocs.length - 1];

            if (result.size() >= hit || topDocs.scoreDocs.length < hit){
                break; // 필요한 수읙 결과를 가져오거나 더 이상 결과가 없으면 종료
            }
        }
        return result;
    }

}
