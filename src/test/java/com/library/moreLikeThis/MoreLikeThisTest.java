package com.library.moreLikeThis;

import com.library.analyer.CustomKoreanAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.nio.file.Path;

public class MoreLikeThisTest {

    @Test
    void test() throws Exception {
        Directory directory = FSDirectory.open(Path.of("/Users/ryujun-yeong/Documents/lucene/src/test/java/com/library/moreLikeThis/index"));

        Analyzer analyzer = new EnglishAnalyzer();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter indexWriter = new IndexWriter(directory, config);

        indexWriter.addDocument(createDocument("Lucene is a powerful and efficient full-text search library for Java applications. It provides advanced capabilities for text indexing and searching."));
        indexWriter.addDocument(createDocument("It is widely used to develop search engines for various types of content. Developers often rely on Lucene to implement search functionalities."));
        indexWriter.addDocument(createDocument("Lucene offers advanced capabilities for text indexing and searching. It is a popular choice for building search applications."));
        indexWriter.addDocument(createDocument("Java developers often rely on Lucene for implementing search functionalities. It is a versatile library for full-text search."));
        indexWriter.addDocument(createDocument("Using Lucene, you can build custom search features tailored to your application. Its flexible APIs make integration seamless."));
        indexWriter.addDocument(createDocument("Lucene provides APIs for indexing and searching documents in an efficient manner. Its high-performance capabilities are crucial for search applications."));
        indexWriter.addDocument(createDocument("Search applications built with Lucene are fast, reliable, and scalable. Its efficient indexing and querying make it a top choice."));
        indexWriter.addDocument(createDocument("Incorporating Lucene into your project can greatly enhance search performance. Its flexibility allows for customization based on your requirements."));
        indexWriter.addDocument(createDocument("Lucene's flexible architecture allows integration with a variety of systems. It is widely used in enterprise-level applications."));
        indexWriter.addDocument(createDocument("With Lucene, you can achieve accurate and relevant search results. Its powerful features enable precision and recall in search queries."));

        indexWriter.commit();
        indexWriter.close();

        String inputString = "How can I use Lucene for document retrieval?";

        IndexReader reader = DirectoryReader.open(directory);

        MoreLikeThis moreLikeThis = new MoreLikeThis(reader);
        moreLikeThis.setAnalyzer(analyzer);   // 텀벡터를 만들기 위한 Analyzer 설정

        String[] fields = new String[1];
        fields[0] = "content";

        IndexSearcher searcher = new IndexSearcher(reader);
        String fieldName = "content";
        Query queryT = new QueryParser(fieldName, analyzer).parse("lucene");
        TopDocs search = searcher.search(queryT, 10);
        ScoreDoc[] scoreDocs = search.scoreDocs;
        StoredFields storedFields = searcher.storedFields();
        for (int i = 0; i < scoreDocs.length; i++) {
            Document document = storedFields.document(scoreDocs[i].doc);
            String s = document.get(fieldName);
            System.out.println("s = " + s);

            Query simQuery = moreLikeThis.like(fieldName, new StringReader(inputString));

            TopDocs related = searcher.search(simQuery, 5);
            for (ScoreDoc rd : related.scoreDocs){
                Document doc = reader.document(rd.doc);
                System.out.println("-> " + doc.get(fieldName));
            }
        }
        reader.close();
    }


    private static Document createDocument(String content) {
        Document doc = new Document();
//        doc.add(new TextField("id", String.valueOf(id), Field.Store.YES));
        doc.add(new TextField("content", content, Field.Store.YES));

        return doc;
    }

}
