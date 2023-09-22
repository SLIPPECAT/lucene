package com.library.similarity;

import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.FileSwitchDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SimilarityTest {

    @Test
    void setUp() throws IOException {
        Path path = Paths.get("src", "test", "resources", "index", "similarity");
        Directory index = FSDirectory.open(path);
        IndexWriterConfig config = new IndexWriterConfig(new KoreanAnalyzer());
        config.setSimilarity(new BM25Similarity());
        IndexWriter writer = new IndexWriter(index, config);

        Document doc = new Document();
        FieldType ft = TextField.TYPE_STORED;

    }

    @Test
    void test(){


    }
}
