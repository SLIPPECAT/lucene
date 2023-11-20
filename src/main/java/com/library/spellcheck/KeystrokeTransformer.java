package com.library.spellcheck;

import com.github.arinyaho.inko4j.Inko;

/*
Inko 클래스는 영어 자판으로 한글을 입력한 경우 이것을 한글로 변환시켜주는
메서드를 포함하고 있다.
ks2ko, kps2ko
 */
public class KeystrokeTransformer {

    public static void main(String[] args) {
        String text = "dkssudgktpdy";
        Inko inko = new Inko(true);

        String s = inko.ks2ko(text);
        String s1 = inko.kps2ko(text);

        System.out.println("s = " + s);
        System.out.println("s1 = " + s1);

    }

}
