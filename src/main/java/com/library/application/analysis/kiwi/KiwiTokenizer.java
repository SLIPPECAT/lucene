package com.library.application.analysis.kiwi;

import com.library.config.FilePathConfig;
import kr.pe.bab2min.Kiwi;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import java.io.IOException;

public class KiwiTokenizer extends Tokenizer {

    private final KiwiTokenReader tokenReader;
    private final CharTermAttribute termAttribute = addAttribute(CharTermAttribute.class);
    private final OffsetAttribute offsetAttribute = addAttribute(OffsetAttribute.class);
    private final PositionIncrementAttribute positionIncrementAttribute = addAttribute(PositionIncrementAttribute.class);
    private final TypeAttribute typeAttribute = addAttribute(TypeAttribute.class);
    private final FilePathConfig filePathConfig;

    public KiwiTokenizer(FilePathConfig filePathConfig) throws Exception {
        super();
        this.filePathConfig = filePathConfig;
        this.tokenReader = new KiwiTokenReader(filePathConfig);
    }

    @Override
    public boolean incrementToken() throws IOException {
        clearAttributes();

        Kiwi.Token currentToken = tokenReader.nextToken();

        if (currentToken != null){
            termAttribute.append(currentToken.form);
            offsetAttribute.setOffset(currentToken.wordPosition, currentToken.wordPosition + currentToken.length);
            positionIncrementAttribute.setPositionIncrement(1);
            return true;
        }

        return false;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
    }

    @Override
    public void end() throws IOException {
        super.end();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

}
