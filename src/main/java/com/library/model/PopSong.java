package com.library.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PopSong {
    private String rank;
    private String song;
    private String artist;
    private String year;
    private String lyrics;
    private String source;

    public PopSong(String song, String lyrics) {
        this.song = song;
        this.lyrics = lyrics;
    }

    public PopSong(String rank, String song, String artist, String year, String lyrics, String source) {
        this.rank = rank;
        this.song = song;
        this.artist = artist;
        this.year = year;
        this.lyrics = lyrics;
        this.source = source;
    }
}
