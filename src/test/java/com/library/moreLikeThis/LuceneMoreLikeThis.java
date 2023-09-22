package com.library.moreLikeThis;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.*;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;

public class LuceneMoreLikeThis {

    @Test
    void test() throws IOException {
        start();
        writerEntries();
        findSilimar("doduck prototype");
    }

    private Directory indexDir;
    private StandardAnalyzer analyzer;
    private IndexWriterConfig config;

    private void start() throws IOException {
        analyzer = new StandardAnalyzer();
        config = new IndexWriterConfig(analyzer);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        indexDir = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/lucene/src/test/java/com/library/moreLikeThis/index"));
    }

    private void writerEntries() throws IOException, IOException {

        IndexWriter indexWriter = new IndexWriter(indexDir, config);
        indexWriter.commit();

        Document doc1 = createDocument("1","doduck","prototype your idea");
        Document doc2 = createDocument("2","doduck","love programming");
        Document doc3 = createDocument("3","We do", "prototype");
        Document doc4 = createDocument("4","We love", "challange");
        indexWriter.addDocument(doc1);
        indexWriter.addDocument(doc2);
        indexWriter.addDocument(doc3);
        indexWriter.addDocument(doc4);

        indexWriter.commit();
        indexWriter.forceMerge(100, true);
        indexWriter.close();
    }

    private Document createDocument(String id, String title, String content) {
        FieldType type = new FieldType();
        type.setIndexOptions(IndexOptions.DOCS);
        type.setStored(true);
        type.setStoreTermVectors(true); //TermVectors are needed for MoreLikeThis

        Document doc = new Document();
        doc.add(new StringField("id", id, Field.Store.YES));
        doc.add(new Field("title", title, type));
        doc.add(new Field("content", content, type));
        return doc;
    }

    private void findSilimar(String searchForSimilar) throws IOException {
        IndexReader reader = DirectoryReader.open(indexDir);
        IndexSearcher indexSearcher = new IndexSearcher(reader);

        MoreLikeThis mlt = new MoreLikeThis(reader);
        mlt.setMinTermFreq(0);
        mlt.setMinDocFreq(0);
        mlt.setFieldNames(new String[]{"title", "content"});
        mlt.setAnalyzer(analyzer);


        Reader sReader = new StringReader(searchForSimilar);
        Query query = mlt.like("title", sReader);

        TopDocs topDocs = indexSearcher.search(query,10);

        for (ScoreDoc scoreDoc : topDocs.scoreDocs ) {
            Document aSimilar = indexSearcher.doc( scoreDoc.doc );
            String similarTitle = aSimilar.get("title");
            String similarContent = aSimilar.get("content");

            System.out.println("====similar finded====");
            System.out.println("title: "+ similarTitle);
            System.out.println("content: "+ similarContent);
        }

    }

}
