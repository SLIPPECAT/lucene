package com.library.lucene.service;


import com.library.lucene.dto.SynonymRequestDto;
import com.library.lucene.dto.SynonymResponseDto;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.CharsRef;
import org.springframework.stereotype.Service;

@Service
public class SynonymService {

    public SynonymResponseDto relateSynonym(SynonymRequestDto requestDto) {
        String synonym = requestDto.getSynonym();
        String target = requestDto.getTarget();
        SynonymMap.Builder builder = new SynonymMap.Builder(true);
        builder.add(new CharsRef(target), new CharsRef(synonym), true);
        return new SynonymResponseDto(requestDto);
    }

    // 쿼리로 검색하여 결과를 확인한다.
}
