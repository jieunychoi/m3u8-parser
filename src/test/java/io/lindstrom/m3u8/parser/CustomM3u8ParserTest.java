package io.lindstrom.m3u8.parser;

import io.lindstrom.m3u8.model.MediaPlaylist;
import io.lindstrom.m3u8.model.MediaSegment;
import io.lindstrom.m3u8.model.Scte35;

import java.io.IOException;

public class CustomM3u8ParserTest {
    public static void main(String[] args) throws IOException {
        MediaPlaylistParser parser = new MediaPlaylistParser();

        MediaPlaylist mediaPlaylist = parser.readPlaylist(M3U8.m3u8Second);
        for (MediaSegment mediaSegment : mediaPlaylist.mediaSegments()) {
            System.out.println(mediaSegment);

            if(mediaSegment.scte35().isPresent())
                System.out.println("scte35 : " +mediaSegment.scte35().get().spliceInfoSection().get().spliceInsert.uniqueProgramID);

            MediaSegment m3 = MediaSegment.builder()
                    .uri("")
                    .duration(3000)
                    .scte35(Scte35.builder().cue("/AAAAAAAAAAAAAAAAAUAAAAAAAACfwAAAAAAAAAAAAAAAAAAAAAAAA==").build()).build();

            System.out.println(m3.toString());


        }


//        String strM3u8 = parser.writePlaylistAsString(mediaPlaylist);
//        System.out.println(strM3u8);

//        MasterPlaylistParser masterPlaylistParser = new MasterPlaylistParser();
//        MasterPlaylist masterPlaylist = masterPlaylistParser.readPlaylist(masterM3u8);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//
//        for (Variant variant : masterPlaylist.variants()) {
//            System.out.println(objectMapper.writeValueAsString(variant));
//
//        }

    }

    static String masterM3u8 = "#EXTM3U\n" +
            "#EXT-X-VERSION:3\n" +
            "#EXT-X-INDEPENDENT-SEGMENTS\n" +
            "#EXT-X-STREAM-INF:BANDWIDTH=14187166,AVERAGE-BANDWIDTH=8942754,RESOLUTION=1920x1080,FRAME-RATE=29.970,CODECS=\"avc1.640029,mp4a.40.2\"\n" +
            "index_1.m3u8\n" +
            "#EXT-X-STREAM-INF:BANDWIDTH=7205990,AVERAGE-BANDWIDTH=4611887,RESOLUTION=1280x720,FRAME-RATE=29.970,CODECS=\"avc1.64001F,mp4a.40.2\"\n" +
            "index_2.m3u8\n";

    static class M3U8{
        static String m3u8 =
                "#EXTM3U\n" +
                        "#EXT-X-VERSION:3\n" +
                        "#EXT-X-TARGETDURATION:7\n" +
                        "#EXT-X-MEDIA-SEQUENCE:55794\n" +
                        "#EXTINF:6.006,\n" +
                        "index_1_55794.ts?m=1595022161\n" +
                        "#EXTINF:6.006,\n" +
                        "index_1_55795.ts?m=1595022161\n" +
                        "#EXTINF:0.167,\n" +
                        "index_1_55796.ts?m=1595022161\n" +
                        "#EXT-OATCLS-SCTE35:/DAlAAAAAyiYAP/wFAUAAAALf+//BX4bw/4AKTGxAAEBAQAAhn/wVw==\n" +
                        "#EXT-X-CUE-OUT:29.997\n" +
                        "#EXTINF:5.839,\n" +
                        "index_1_55797.ts?m=1595022161\n" +
                        "#EXT-X-CUE-OUT-CONT:ElapsedTime=5.839,Duration=29.997,SCTE35=/DAlAAAAAyiYAP/wFAUAAAALf+//BX4bw/4AKTGxAAEBAQAAhn/wVw==\n" +
                        "#EXTINF:6.006,\n" +
                        "index_1_55798.ts?m=1595022161\n" +
                        "#EXT-X-CUE-OUT-CONT:ElapsedTime=11.845,Duration=29.997,SCTE35=/DAlAAAAAyiYAP/wFAUAAAALf+//BX4bw/4AKTGxAAEBAQAAhn/wVw==\n" +
                        "#EXTINF:6.006,\n" +
                        "index_1_55799.ts?m=1595022161\n" +
                        "#EXT-X-CUE-OUT-CONT:ElapsedTime=17.851,Duration=29.997,SCTE35=/DAlAAAAAyiYAP/wFAUAAAALf+//BX4bw/4AKTGxAAEBAQAAhn/wVw==\n" +
                        "#EXTINF:6.006,\n" +
                        "index_1_55800.ts?m=1595022161\n" +
                        "#EXT-X-CUE-OUT-CONT:ElapsedTime=23.857,Duration=29.997,SCTE35=/DAlAAAAAyiYAP/wFAUAAAALf+//BX4bw/4AKTGxAAEBAQAAhn/wVw==\n" +
                        "#EXTINF:6.006,\n" +
                        "index_1_55801.ts?m=1595022161\n" +
                        "#EXT-X-CUE-OUT-CONT:ElapsedTime=29.863,Duration=29.997,SCTE35=/DAlAAAAAyiYAP/wFAUAAAALf+//BX4bw/4AKTGxAAEBAQAAhn/wVw==\n" +
                        "#EXTINF:0.133,\n" +
                        "index_1_55802.ts?m=1595022161\n" +
                        "#EXT-X-CUE-IN\n" +
                        "#EXTINF:5.873,\n" +
                        "index_1_55803.ts?m=1595022161\n" +
                        "#EXTINF:6.006,\n" +
                        "index_1_55804.ts?m=1595022161\n";

        static String m3u8Second = "" +
                "#EXTM3U\n" +
                "#EXT-X-VERSION:3\n" +
                "#EXT-X-TARGETDURATION:7\n" +
                "#EXT-X-MEDIA-SEQUENCE:59263\n" +
                "#EXTINF:6.006,\n" +
                "https://acee2b4c336e2e6f.mediapackage.ap-northeast-2.amazonaws.com/out/v1/1acd2d57ce0149a1bb5b6999169304fe/index_1_59263.ts?m=1595022161\n" +
                "#EXTINF:6.006,\n" +
                "https://acee2b4c336e2e6f.mediapackage.ap-northeast-2.amazonaws.com/out/v1/1acd2d57ce0149a1bb5b6999169304fe/index_1_59264.ts?m=1595022161\n" +
                "#EXTINF:3.804,\n" +
                "https://acee2b4c336e2e6f.mediapackage.ap-northeast-2.amazonaws.com/out/v1/1acd2d57ce0149a1bb5b6999169304fe/index_1_59265.ts?m=1595022161\n" +
                "#EXT-OATCLS-SCTE35:/DAlAAFy1UWYAP/wFAUAAAAMf+/+Al9Tov4AKTGxAAEBAQAAAPWP6Q==\n" +
                "#EXT-X-CUE-OUT:29.997\n" +
                "#EXTINF:4.204,\n" +
                "https://acee2b4c336e2e6f.mediapackage.ap-northeast-2.amazonaws.com/out/v1/1acd2d57ce0149a1bb5b6999169304fe/index_1_59266.ts?m=1595022161\n" +
                "#EXT-X-CUE-OUT-CONT:ElapsedTime=4.204,Duration=29.997,SCTE35=/DAlAAFy1UWYAP/wFAUAAAAMf+/+Al9Tov4AKTGxAAEBAQAAAPWP6Q==\n" +
                "#EXTINF:6.006,\n" +
                "https://acee2b4c336e2e6f.mediapackage.ap-northeast-2.amazonaws.com/out/v1/1acd2d57ce0149a1bb5b6999169304fe/index_1_59267.ts?m=1595022161\n" +
                "#EXT-X-CUE-OUT-CONT:ElapsedTime=10.210,Duration=29.997,SCTE35=/DAlAAFy1UWYAP/wFAUAAAAMf+/+Al9Tov4AKTGxAAEBAQAAAPWP6Q==\n" +
                "#EXTINF:6.006,\n" +
                "https://acee2b4c336e2e6f.mediapackage.ap-northeast-2.amazonaws.com/out/v1/1acd2d57ce0149a1bb5b6999169304fe/index_1_59268.ts?m=1595022161";
    }
}
