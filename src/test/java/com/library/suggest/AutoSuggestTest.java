package com.library.suggest;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.memory.MemoryIndex;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.suggest.FileDictionary;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.fst.FSTCompletionLookup;
import org.apache.lucene.search.suggest.fst.WFSTCompletionLookup;
import org.apache.lucene.search.suggest.tst.TSTLookup;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class AutoSuggestTest {

    @Test
    void test() throws IOException, ParseException {
//        MemoryIndex directory = new MemoryIndex();
        Directory directory = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/lucene/src/test/java/com/library/suggest/index"));

        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter indexWriter = new IndexWriter(directory, config);
        indexWriter.addDocument(createDocument("apple", 100));
        indexWriter.addDocument(createDocument("banana", 80));
        indexWriter.addDocument(createDocument("cherry", 120));
        indexWriter.addDocument(createDocument("date", 90));
        indexWriter.commit();
        indexWriter.close();

//        Lookup lookup = new WFSTCompletionLookup(directory, "suggestField");
        Lookup lookup = new TSTLookup(directory, "suggestField");
//        Lookup lookup = new FSTCompletionLookup(directory, "suggestField");

        String prefix = "b";
        int numSuggestions = 5;

        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory));
        Query query = new QueryParser("suggestField", analyzer).parse("banana");
        TopDocs search = searcher.search(query, 10);
        ScoreDoc[] scoreDocs = search.scoreDocs;
        StoredFields storedFields = searcher.storedFields();
        for (int i = 0; i < scoreDocs.length; i++) {
            Document document = storedFields.document(scoreDocs[i].doc);
            String s = document.get("suggestField");
            System.out.println("s = " + s);
        }

        System.out.println("Suggestions for prefix '" + prefix +"':");

        Dictionary dictionary = new FileDictionary(new FileInputStream("/Users/ryujun-yeong/Documents/lucene/src/test/java/com/library/suggest/data/dummy.txt"));
        lookup.build(dictionary);

        List<Lookup.LookupResult> suggestions = lookup.lookup(prefix, false, numSuggestions);
        for (Lookup.LookupResult suggestion : suggestions){
            System.out.println("- " + suggestion);
        }
    }

    private static Document createDocument(String word, int weight) {
        Document doc = new Document();
        doc.add(new TextField("suggestField", word, Field.Store.YES));
        doc.add(new TextField("weightField", String.valueOf(weight), Field.Store.YES));
        return doc;
    }
}
