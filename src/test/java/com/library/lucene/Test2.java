package com.library.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

public class Test2 {

    @BeforeEach
    void setUp() throws IOException {
        IndexSearcher[] searchers;

        String[] animals = {
                "Lion", "Tiger", "Leopard", "Cheetah", "Giraffe", "Elephant", "Kangaroo", "Koala", "Panda", "Penguin",
                "Hippopotamus", "Zebra", "Rhino", "Gorilla", "Dolphin", "Whale", "Shark", "Crocodile", "Alligator", "Polar Bear",
                "Camel", "Ostrich", "Llama", "Hedgehog", "Kangaroo", "Sloth", "Peacock", "Puma", "Jaguar"
        };
        Analyzer analyzer = new WhitespaceAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        Directory aTOmDirectory = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/projects/common/lib/lucene/src/test/resources/atom"));
        Directory nTOzDirectory = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/projects/common/lib/lucene/src/test/resources/ntoz"));

        IndexWriter aTOmWriter = new IndexWriter(aTOmDirectory, config);
        IndexWriter nTOzWriter = new IndexWriter(nTOzDirectory, config);

        for (int i = animals.length; i >= 0; i--) {
            Document document = new Document();
            String animal = animals[i];

            document.add(new Field("animal", animal, TextField.TYPE_STORED));

            if (animal.charAt(0) < 'n'){
                aTOmWriter.addDocument(document);
            } else {
                nTOzWriter.addDocument(document);
            }
            aTOmDirectory.close();
            nTOzDirectory.close();

            searchers = new IndexSearcher[2];
            searchers[0] = new IndexSearcher(DirectoryReader.open(aTOmDirectory));
            searchers[1] = new IndexSearcher(DirectoryReader.open(nTOzDirectory));
        }
    }

    @Test
    void test_multi(){


    }



}
