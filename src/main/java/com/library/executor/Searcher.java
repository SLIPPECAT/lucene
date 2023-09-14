package com.library.executor;

import com.library.analyer.NgramAnalyzer;
import com.library.model.PopSong;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.FeatureField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StoredFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.similarities.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Searcher {
    Analyzer analyzer = new StandardAnalyzer();

    public List<PopSong> search(String text) throws ParseException, IOException {
        Path indexPath = Path.of("/Users/ryujun-yeong/Documents/projects/common/lib/lucene/index");
        Directory index = FSDirectory.open(indexPath);
        QueryParser queryParser = new QueryParser("lyrics", analyzer);
        Query query = queryParser.parse(text);
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        ClassicSimilarity classicSimilarity = new ClassicSimilarity();
        searcher.setSimilarity(classicSimilarity);
        ScoreDoc[] hits = searcher.search(query, 3).scoreDocs;
        StoredFields storedFields = searcher.storedFields();
        
        List<PopSong> popSongs = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = storedFields.document(hits[i].doc);
            String song = hitDoc.get("song");
            String lyrics = hitDoc.get("lyrics");
            popSongs.add(new PopSong(song, lyrics));
            Explanation explanation  =
                    searcher.explain(query, hits[i].doc);
            System.out.println("----------------------");
            System.out.println("song = " + song);
            System.out.println("explanation = " + explanation.toString());
        }
        reader.close();
        index.close();
        return popSongs;
    }
}
