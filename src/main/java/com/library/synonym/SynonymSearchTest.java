package com.library.synonym;

import org.apache.lucene.analysis.Analyzer;
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
import java.nio.file.Paths;

public class SynonymSearchTest {

    public static void main(String[] args) throws IOException, ParseException {
        Directory index = FSDirectory.open(Paths.get("lucene/luceneidx"));

        Analyzer searchTimeAnalyzer = new WhitespaceAnalyzer();

        IndexReader reader = DirectoryReader.open(index);

        IndexSearcher searcher = new IndexSearcher(reader);

        QueryParser parser = new QueryParser("test", searchTimeAnalyzer);

        Query query = parser.parse("plane");

        TopDocs hits = searcher.search(query, 10);

        for (int i = 0; i < hits.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = hits.scoreDocs[i];
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.get("title") + "by " + doc.get("author"));
        }
    }
}
