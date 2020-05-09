package io.lindstrom.m3u8.parser;

import io.lindstrom.m3u8.model.SegmentMap;

enum SegmentMapParser implements Attribute<SegmentMap, SegmentMap.Builder> {
    URI {
        @Override
        public void read(SegmentMap.Builder builder, String value) throws PlaylistParserException {
            builder.uri(value);
        }

        @Override
        public void write(SegmentMap value, TextBuilder textBuilder) {
            textBuilder.addQuoted(name(), value.uri());
        }
    },

    BYTERANGE {
        @Override
        public void read(SegmentMap.Builder builder, String value) throws PlaylistParserException {
            builder.byteRange(ParserUtils.parseByteRange(value));
        }

        @Override
        public void write(SegmentMap value, TextBuilder textBuilder) {
            value.byteRange().map(ParserUtils::writeByteRange).ifPresent(v ->
                    textBuilder.addQuoted(name(), v));
        }
    }
}
