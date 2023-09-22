package com.library.util;

public class UtilTester {

    public static void main(String[] args) {
        char[] a = "리그오브레전드".toCharArray();
        char[] b = "리오레".toCharArray();

        Jaccard jaccardMeasure = new Jaccard();
//        float jaccard = jaccardMeasure.jaccard(a, b);
        float jaccard = jaccardMeasure.jaccard(b, a);
        System.out.println("jaccard = " + jaccard);

        LevensteinDistance levensteinDistance = new LevensteinDistance();
        int i = levensteinDistance.levensteinDistance(a, b);
        System.out.println("levensteinDistance = " + i);
    }

}
