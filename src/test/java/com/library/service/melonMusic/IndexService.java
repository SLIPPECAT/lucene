package com.library.service.melonMusic;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexService {

    @Test
    void index() throws IOException {
        Directory index = FSDirectory.open(Paths.get("src/test/resources/MusicTop50/index"));
        Analyzer analyzer = new NgramAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        Path filePath = Paths.get("src/test/resources/MusicTop50/top50.tsv");
        try (IndexWriter writer = new IndexWriter(index, config)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(filePath))));
            String line;
            while ((line = reader.readLine()) != null){
                Document document = new Document();
                String[] split = line.split("\t");
                Field.Store fs = Field.Store.YES;
                System.out.println(line);

                document.add(new TextField("rank", split[0], fs));
                document.add(new TextField("title", split[1], fs));
                document.add(new TextField("artist", split[2], fs));

                writer.addDocument(document);
            }
        }
        index.close();
    }
}
