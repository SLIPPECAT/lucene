package com.library.lucene.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchRequestDto {

    String clubName;

    String hostName;
    public SearchRequestDto(String clubName, String hostName) {
        this.clubName = clubName;
        this.hostName = hostName;
    }
}
