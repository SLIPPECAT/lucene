package com.library.executor;

import com.library.config.PathConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
@Slf4j
public class Indexer {

    private final PathConfig pathConfig;
    Analyzer analyzer = new StandardAnalyzer();

    public void index(){
        String indexPath = pathConfig.getIndexPath();
        String fileName = "billboard_lyrics_1964-2015.csv";
        String file = indexPath + fileName;

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {

            String line;
            int lineNum = 0;
            bufferedReader.readLine(); // 헤더 라인 없애기

            Directory index = FSDirectory.open(Path.of(indexPath));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter writer = new IndexWriter(index, config);

            while ((line = bufferedReader.readLine()) != null) {
                lineNum++;
                String[] split = line.split(",");
                String song = split[1];
                String lyrics = split[4];

                Document songDoc = new Document();
                songDoc.add(new Field("song", song, TextField.TYPE_STORED));
                songDoc.add(new Field("lyrics", lyrics, TextField.TYPE_STORED));
                writer.addDocument(songDoc);
            }
            writer.close();
            index.close();
            System.out.println(lineNum);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
