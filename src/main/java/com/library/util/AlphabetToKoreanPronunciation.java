package com.library.util;

import java.util.HashMap;
import java.util.Map;

public class AlphabetToKoreanPronunciation {
    public String convertToKoreanPronunciation(String englishText) {
        Map<Character, String> pronunciationMap = createPronunciationMap();

        StringBuilder koreanPronunciation = new StringBuilder();
        for (char c : englishText.toCharArray()) {
            String pronunciation = pronunciationMap.getOrDefault(c, String.valueOf(c));
            koreanPronunciation.append(pronunciation);
        }
        return koreanPronunciation.toString();
    }

    public Map<Character, String> createPronunciationMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('a', "에이");
        map.put('b', "비");
        map.put('c', "씨");
        map.put('d', "디");
        map.put('e', "이");
        map.put('f', "에프");
        map.put('g', "지");
        map.put('h', "에이치");
        map.put('i', "아이");
        map.put('j', "제이");
        map.put('k', "케이");
        map.put('l', "엘");
        map.put('m', "엠");
        map.put('n', "앤");
        map.put('o', "오");
        map.put('p', "피");
        map.put('q', "큐");
        map.put('r', "알");
        map.put('s', "에스");
        map.put('t', "티");
        map.put('u', "유");
        map.put('v', "브이");
        map.put('w', "더블유");
        map.put('x', "엑스");
        map.put('y', "와이");
        map.put('z', "지");
        map.put('&', "앤");
        map.put('#', "샾");
        return map;
    }
}

