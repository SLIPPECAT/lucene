package com.library.synonym;//package com.example.luceneutil.synonym;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.Tokenizer;
//import org.apache.lucene.analysis.core.KeywordAnalyzer;
//import org.apache.lucene.analysis.core.WhitespaceTokenizer;
//import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
//import org.apache.lucene.analysis.custom.CustomAnalyzer;
//import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
//import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
//import org.apache.lucene.analysis.synonym.SynonymGraphFilterFactory;
//import org.apache.lucene.analysis.synonym.SynonymMap;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.*;
//import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.CharsRef;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SynonymIndexTest {
//
//    public static void main(String[] args) throws IOException, ParseException {
//        // 개별적으로 동의어 입력
//        SynonymMap.Builder builder = new SynonymMap.Builder();
//        builder.add(new CharsRef("aeroplane"), new CharsRef("plane"), true);
//        final SynonymMap map = builder.build();
//
//        // 파일을 이용한 동의어 입력
//        Map<String, String> sffargs = new HashMap<>();
//        sffargs.put("synonyms", "synonyms.txt");
//        sffargs.put("ignoreCase", "true");
//
//        CustomAnalyzer.Builder customAnalyzerBuilder = CustomAnalyzer.builder()
//                .withTokenizer(WhitespaceTokenizerFactory.class)
//                .addTokenFilter(SynonymGraphFilterFactory.class, sffargs);
//        CustomAnalyzer customAnalyzer = customAnalyzerBuilder.build();
//
//        Analyzer indexTimeAnalyzer = new Analyzer() {
//            @Override
//            protected TokenStreamComponents createComponents(String fieldName) {
//                Tokenizer tokenizer = new WhitespaceTokenizer();
//                SynonymGraphFilter synFilter =
//                        new SynonymGraphFilter(tokenizer, map, true);
//                return new TokenStreamComponents(tokenizer, synFilter);
//            }
//        };
//
//        Directory index = FSDirectory.open(Paths.get("lucene/luceneidx"));
//
//        Map<String, Analyzer> perFieldAnalyzers = new HashMap<>();
//
//        perFieldAnalyzers.put("years", new KeywordAnalyzer());
//
////        Analyzer analyzer = new PerFieldAnalyzerWrapper(
////                indexTimeAnalyzer, perFieldAnalyzers);
//
//        Analyzer analyzer = new PerFieldAnalyzerWrapper(
//                customAnalyzer, perFieldAnalyzers);
//
//        IndexWriterConfig config = new IndexWriterConfig(analyzer);
//        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//
//        IndexWriter writer = new IndexWriter(index, config);
//
//        Document aeroplaneDoc = new Document();
//        aeroplaneDoc.add(new TextField("title", "Aeroplane", Field.Store.YES));
//        aeroplaneDoc.add(new TextField("author", "Red Hot Chili Peppers", Field.Store.YES));
//        aeroplaneDoc.add(new TextField("year", "1995", Field.Store.YES));
//        aeroplaneDoc.add(new TextField("album", "One HOt Minute", Field.Store.YES));
//        aeroplaneDoc.add(new TextField("text", "I like pleasure spiked with pain and music is my aeroplane ...", Field.Store.YES));
//
//        writer.addDocument(aeroplaneDoc);
//        writer.commit();
//    }
//}
