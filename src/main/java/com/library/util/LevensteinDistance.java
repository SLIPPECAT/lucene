package com.library.util;

public class LevensteinDistance {

    public int levensteinDistance(char[] s, char[] t){
        int m = s.length;
        int n = t.length;
        int[][] d = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <=n ; j++) {
            d[0][j] = j;
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= m; i++) {
                if (s[i-1] == t[j-1]){
                    d[i][j] = d[i-1][j-1];
                } else {
                    d[i][j] = Math.min(Math.min(
                            d[i-1][j] + 1,
                            d[i][j-1] + 1),
                            d[i-1][j-1] + 1);
                }
            }
        }
        return d[m][n];
    }
}
