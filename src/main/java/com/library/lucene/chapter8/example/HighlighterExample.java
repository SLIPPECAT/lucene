package com.library.lucene.chapter8.example;//package com.club.volleyball.lucence.chapter8.example;
//
//import com.club.volleyball.lucence.chapter8.CsvLoader;
//import com.club.volleyball.lucence.chapter8.IndexServiceImpl;
//import com.club.volleyball.lucence.chapter8.RestaurantInfoVo;
//import com.club.volleyball.domain.index.analyzer.CustomKoreanAnalyzer;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.TokenStream;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.highlight.*;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//
//import java.nio.file.Paths;
//import java.util.List;
//
//public class HighlighterExample {
//
//    public static void main(String[] args) {
//        CsvLoader csvHelper = new CsvLoader();
//        List<RestaurantInfoVo> tourInfoList = csvHelper.readRestaurantnfo();
//
//        try {
//            Directory index = FSDirectory.open(Paths.get("/Users/ryujun-yeong/Documents/algorithm/club/index/chapter8"));
//
//            IndexServiceImpl indexServiceImpl = new IndexServiceImpl();
//            indexServiceImpl.indexRestaurantInfo(index, tourInfoList);
//
//            IndexReader reader = DirectoryReader.open(index);
//            IndexSearcher searcher = new IndexSearcher(reader);
//
//            Analyzer analyzer = new CustomKoreanAnalyzer();
//            QueryParser qp = new QueryParser("description", analyzer);
//            Query query = qp.parse("제주도");
//
//            TopDocs hits = searcher.search(query, 10);
//
//            /* 하이라이터 시작 */
//
//            Formatter formatter = new SimpleHTMLFormatter();
//
//            QueryScorer scorer = new QueryScorer(query);
//
//            Highlighter highlighter = new Highlighter(formatter, scorer);
//
//            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 10);
//
//            highlighter.setTextFragmenter(fragmenter);
//
//            for (int i = 0; i < hits.scoreDocs.length; i++){
//                int docid = hits.scoreDocs[i].doc;
//                Document doc = searcher.doc(docid);
//                String title = doc.get("restaurantName");
//
//                System.out.println("음식점 이름 : " + title);
//
//                String text = doc.get("description");
//
//                TokenStream stream = analyzer.tokenStream("description", text);
//
//                String[] frags = highlighter.getBestFragments(stream, text, 10);
//                for (String frag : frags){
//                    System.out.println(frag);
//                }
//                System.out.println("========================");
//            }
//            index.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
