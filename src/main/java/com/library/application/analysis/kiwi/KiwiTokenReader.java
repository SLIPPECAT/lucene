package com.library.application.analysis.kiwi;

import com.library.config.FilePathConfig;
import kr.pe.bab2min.Kiwi;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;

public class KiwiTokenReader extends Reader {

    public static void main(String[] args) {

    }

    private Kiwi.Token[] tokens;
    private int tokenIndex;
    private int currentPosition;
    private int currentTokenLength;
    private final FilePathConfig filePathConfig;

    public KiwiTokenReader(FilePathConfig filePathConfig) {
        this.filePathConfig = filePathConfig;
    }

    KiwiTokenReader(String text, FilePathConfig filePathConfig) throws Exception {
        this.tokens = tokenizeWithKiwi(text);
        this.filePathConfig = filePathConfig;
        this.tokenIndex = 0;
        this.currentPosition = 0;
        this.currentTokenLength = 0;
    }
    private Kiwi.Token[] tokenizeWithKiwi(String text) throws Exception {
            Kiwi kiwi = Kiwi.init(filePathConfig.getKiwiModelGenerator());
            Kiwi.Token[] kiwiTokens = kiwi.tokenize(text, Kiwi.Match.allWithNormalizing);
            return kiwiTokens;
    }


    @Override
    public int read(@NotNull char[] cbuf, int off, int len) throws IOException {
        if (tokens == null || tokenIndex >= tokens.length){
            return -1;  // 읽을 데이터가 없음
        }

        Kiwi.Token currentToken = tokens[tokenIndex];
        int remainingTokenLength = currentToken.length - currentTokenLength;
        int bytesRead = Math.min(len, currentToken.length - currentTokenLength);

        System.arraycopy(currentToken.form.toCharArray(), currentTokenLength, cbuf, off, bytesRead);

        currentTokenLength += bytesRead;
        currentPosition += bytesRead;

        if (currentTokenLength >= currentToken.length){
            tokenIndex++;
            currentTokenLength = 0;
        }

        return bytesRead;
    }

    @Override
    public void close() throws IOException {

    }

    public Kiwi.Token nextToken() {
        if (tokens != null && tokenIndex < tokens.length){
            return tokens[tokenIndex++];
        }
        return null;
    }
}
