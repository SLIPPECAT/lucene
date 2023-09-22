//package com.library.analyzer;
//
//public class KoreanMorphemeAnalyzerTest {
//
//    public interface KoreanMorphemeAnalyzer {
//        void analyze(String contents);
//        void printResult();
//        String getName();
//    }
//
//    public static class ArirangMorphemeAnalyzer implements KoreanMorphemeAnalyzer {
//        private WordSegmentAnalyzer wordSegmentAnalyzer;
//        private List<List<AnalysisOut>> resultList;
//
//        public ArirangMorphemeAnalyzer(){
//            wordSegmentAnalyzer = new WordSegmentAnalzyer();
//        }
//
//        @Override
//        public void analyze(String contents){
//            try {
//                resultList = wordSegmentAnalyzer.analyze(contents);
//            } catch (MorphException e){
//                e.printstackTrace();
//            }
//        }
//
//        @Override
//        public void printResult(){
//            System.out.println(resultList);
//        }
//
//        @Override
//        public String getName(){
//            return "ArirangMorphemeAnalyzer;";
//        }
//    }
//
//
//}
