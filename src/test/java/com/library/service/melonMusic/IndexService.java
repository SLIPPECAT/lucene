package com.library.service.melonMusic;

import com.library.analyer.NgramAnalyzer;
import com.library.analyer.SynonymAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.CharsRef;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IndexService {

    @Test
    void index() throws IOException {
        Directory index = FSDirectory.open(Paths.get("src/test/resources/MusicTop50/index"));
//        ==================== [ KoreanAnalyzer ] ====================
//        Analyzer analyzer = new KoreanAnalyzer();

//        ==================== [ NGramAnalyzer ] ====================
        Analyzer analyzer = new NgramAnalyzer();

//        ==================== [ SynonymAnalyzer ] ====================
//        SynonymMap.Builder builder = new SynonymMap.Builder(true);
//        builder.add(new CharsRef("사랑"), new CharsRef("love"), true);
//        Analyzer analyzer = new SynonymAnalyzer(builder.build());

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//        config.setSimilarity(new BM25Similarity());

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
