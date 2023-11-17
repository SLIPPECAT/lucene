package com.library.analyzer;

import org.junit.jupiter.api.Test;
import org.snu.ids.kkma.ma.MExpression;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;

import java.util.List;

public class KkmaAnalyzerTest {

    @Test
    void test_KkmaAnalyzerTest() throws Exception {
        String sentenceString = "오메가3 : 트루엔 알티지오메가3 로얄제리 : 제품간 큰 차이는 없습니다 밀크씨슬 : 리틀약사 실리부스터, 네이처스팜의 토라타민, 웰버의 셀디펜더 등 폴리코사놀 : 제품간 큰 차이 없습니다 비오틴 : 비오틴골드 비타민c : ydy 리포좀 비타민c, 유한양행 일반의약품 코큐텐 : 비타민b와 같이 있는 ydy액티브큐텐 루테인 : ydy 오큐클리어, J&ampH바이오의 아이포뮬러 등 달맞이 종자유 : J&ampH바이오지엘에이 등 이렇게 참고 가능합니다";
        MorphemeAnalyzer morphemeAnalyzer = new MorphemeAnalyzer();

        morphemeAnalyzer.createLogger(null);

        List<MExpression> expressions = morphemeAnalyzer.analyze(sentenceString);

        expressions= morphemeAnalyzer.postProcess(expressions);
        expressions= morphemeAnalyzer.leaveJustBest(expressions);

        List<Sentence> sentences = morphemeAnalyzer.divideToSentences(expressions);
        for (int i = 0; i < sentences.size(); i++) {
            Sentence st = sentences.get(i);
            System.out.println("==================== " + st.getSentence());

            for (int j = 0; j < st.size(); j++) {
                System.out.println(st.get(j));
            }
        }
        morphemeAnalyzer.closeLogger();

    }



}
