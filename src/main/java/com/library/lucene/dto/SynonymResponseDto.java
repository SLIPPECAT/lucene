package com.library.lucene.dto;

public class SynonymResponseDto {

    private String target;
    private String synonym;

    public SynonymResponseDto() {
    }

    public SynonymResponseDto(String target, String synonym) {
        this.target = target;
        this.synonym = synonym;
    }

    public SynonymResponseDto(SynonymRequestDto requestDto) {
        this.target = requestDto.getTarget();
        this.synonym = requestDto.getSynonym();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }
}
