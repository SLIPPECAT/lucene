package com.library.synonym;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LuceneSearchTest {

    public static void main(String[] args) throws ParseException, IOException {

        Path path = Paths.get("lucene/luceneidx");

        Directory index = FSDirectory.open(path);

        IndexReader reader = DirectoryReader.open(index);

        QueryParser parser = new QueryParser("title", new WhitespaceAnalyzer());

        Query query = parser.parse("+Deep +search");

        IndexSearcher searcher = new IndexSearcher(reader);

        TopDocs hits = searcher.search(query, 10);

        for (int i = 0; i < hits.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = hits.scoreDocs[i];

            Document doc = reader.document(scoreDoc.doc);

            System.out.println(doc.get("title") + " : " + scoreDoc.score);

        }
    }
}
