package io.lindstrom.m3u8.parser;

import io.lindstrom.m3u8.model.MediaPlaylist;
import io.lindstrom.m3u8.model.MediaSegment;
import io.lindstrom.m3u8.model.Scte35;

import java.util.ArrayList;
import java.util.List;

public class MediaSegmentTest {
    public static void main(String[] args) {
        MediaSegment mediaSegment = MediaSegment.builder()
                .cueOut(30.0f)
                .scte35(Scte35.builder().cue("/AAAAAAAAAAAAAAAAAUAAAAAAAAAAQAAAAAAAAAAAAAAAAAAAAAAAA==").build())
                .duration(10.0)
                .uri("mediaChunk.url")
                .build();

        ArrayList pitches = new ArrayList();
        pitches.add(mediaSegment);

        MediaPlaylist mediaPlaylist = MediaPlaylist.builder()
                .targetDuration(12)
                .mediaSequence(1)
                .version(3)
                .ongoing(true)
                .addAllMediaSegments(pitches).build();

        System.out.println(new MediaPlaylistParser().writePlaylistAsString(mediaPlaylist));

    }
}
