package com.library.analyzer;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.Eojeol;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SEunjeonExample {

    private String sentenceString;

    @BeforeAll
    void init(){
        sentenceString = "마산을 대표하는 음식 중에 하나가 바로 '아구찜'이다. " +
                "마산 오동동에 가면 마산의 명물인 아구거리가 형성되어 있는데, 거리의 음식점 중 한 곳이 이곳이다. " +
                "갈분가루를 쓰지 않아 걸죽하지 않다는 이 􏰅만의 양념과 호텔에서의 경력이 밑바탕이 된 철저한 서비스 정신과 친절이 바로 이 곳의 인기비결. " +
                "알싸한 양념과 구수한 아구의 고기맛이 제대로 어우러진 마산아구찜 의진수를맛볼수있는곳이다.";
    }

    @Test
    void analyze_morpheme(){
        System.out.println("======== 형태소 분석 ========");
        for (LNode node : Analyzer.parseJava(sentenceString)){
            System.out.println(node.morpheme());
        }
    }

    @Test
    void analyze_eojeol(){
        System.out.println("======== 어절 분석 ========");
        for (Eojeol eojeol: Analyzer.parseEojeolJava(sentenceString)){
            System.out.println("==어절 : ");
            for (LNode node : eojeol.nodesJava()){
                System.out.println(node.morpheme());
            }
        }
    }

    @Test
    void add_user_dictionary(){
        System.out.println("======== 사용자 사전 추가 ========");
        Analyzer.setUserDict(Arrays.asList("케크케").iterator());
        for (LNode node: Analyzer.parseJava("케크케가 맛있다")){
            System.out.println(node.morpheme());
        }
    }

    @Test
    void adapt_infinitive(){
        System.out.println("======== 활용어 원형 적용 ========");
        for (LNode node: Analyzer.parseJava("빨라짐")){
            for (LNode node2: node.deInflectJava()) {
                System.out.println(node2.morpheme());
            }
        }
    }

    @Test
    void decompose_compound_noun(){
        System.out.println("======== 복합명사 분해 ========");
        for (LNode node: Analyzer.parseJava("아구찜")){
            System.out.println("복합명사\n" + node.morpheme());
            System.out.println("분해결과");
            for (LNode node2: node.deCompoundJava()) {
                System.out.println(node2.morpheme());
            }
        }
    }

}