package com.library.csv;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CsvToIndex {

    public static void main(String[] args) {
        csvLoad();
    }

    public static void csvLoad(){
        String csvFile = "/Users/ryujun-yeong/Documents/projects/common/lib/lucene/src/main/resources/data/billboard_lyrics_1964-2015.csv";
        Path path = Paths.get("/Users/ryujun-yeong/Documents/projects/common/lib/lucene/index");
        Analyzer analyzer = new StandardAnalyzer();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile)));) {

            String line;
            int lineNum = 0;
            bufferedReader.readLine(); // 헤더 라인 없애기

            Directory index = FSDirectory.open(path);
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
//            config.setSimilarity(new ClassicSimilarity());
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
