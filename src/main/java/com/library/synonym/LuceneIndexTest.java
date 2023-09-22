package com.library.synonym;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LuceneIndexTest {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("lucene/luceneidx");

        Directory index = FSDirectory.open(path);

        Map<String, Analyzer> perFieldAnalyzers = new HashMap<>();

        CharArraySet stopWords = new CharArraySet(Arrays.asList("a", "an", "the"), true);

        perFieldAnalyzers.put("pages", new StopAnalyzer(stopWords));

        perFieldAnalyzers.put("title", new WhitespaceAnalyzer());

        Analyzer analyzer = new PerFieldAnalyzerWrapper(
                new EnglishAnalyzer(), perFieldAnalyzers);

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter writer = new IndexWriter(index, config);

        Document dl4s = new Document();
        dl4s.add(new TextField("title", "Deep learning for search", Field.Store.YES));
        dl4s.add(new TextField("page", "Living in the information age ...", Field.Store.YES));

        Document rs = new Document();
        rs.add(new TextField("title", "Relevant search", Field.Store.YES));
        rs.add(new TextField("page", "Getting a search to behave ...", Field.Store.YES));

        writer.addDocument(dl4s);
        writer.addDocument(rs);
        writer.commit();
        writer.close();
    }
}
