package com.library.analyzer;

import org.apache.lucene.analysis.ko.morph.AnalysisOutput;
import org.apache.lucene.analysis.ko.morph.MorphException;
import org.apache.lucene.analysis.ko.morph.WordSegmentAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ArirangAnalyzerTest {

    @Test
    void arirangAnalyzerTest() throws MorphException {
        String sentenceString = "오메가3 : 트루엔 알티지오메가3 로얄제리 : 제품간 큰 차이는 없습니다 밀크씨슬 : 리틀약사 실리부스터, 네이처스팜의 토라타민, 웰버의 셀디펜더 등 폴리코사놀 : 제품간 큰 차이 없습니다 비오틴 : 비오틴골드 비타민c : ydy 리포좀 비타민c, 유한양행 일반의약품 코큐텐 : 비타민b와 같이 있는 ydy액티브큐텐 루테인 : ydy 오큐클리어, J&ampH바이오의 아이포뮬러 등 달맞이 종자유 : J&ampH바이오지엘에이 등 이렇게 참고 가능합니다";

        WordSegmentAnalyzer wordSegmentAnalyzer = new WordSegmentAnalyzer();

        List<List<AnalysisOutput>> analyze = wordSegmentAnalyzer.analyze(sentenceString);

        analyze.forEach(i -> i.forEach(System.out::println));
    }

}
