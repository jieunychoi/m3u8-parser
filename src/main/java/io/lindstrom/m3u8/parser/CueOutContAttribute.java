package io.lindstrom.m3u8.parser;

import com.nfl.scte35.decoder.Scte35Decoder;
import com.nfl.scte35.decoder.exception.DecodingException;
import com.nfl.scte35.decoder.model.SpliceInfoSection;
import io.lindstrom.m3u8.model.CueOutCont;
import io.lindstrom.m3u8.model.Scte35;

import java.util.Map;

/*
 * #EXT-X-CUE-OUT-CONT:<attribute-list>
 */
enum CueOutContAttribute implements Attribute<CueOutCont, CueOutCont.Builder> {
    ElapsedTime {
        @Override
        public void read(CueOutCont.Builder builder, String value) {
            builder.elapsedTime(Float.parseFloat(value));
        }

        @Override
        public void write(CueOutCont value, TextBuilder textBuilder) {
            textBuilder.add(key(), String.valueOf(value.elapsedTime()));
        }
    },

    Duration {
        @Override
        public void read(CueOutCont.Builder builder, String value) {
            builder.duration(Float.parseFloat(value));
        }

        @Override
        public void write(CueOutCont value, TextBuilder textBuilder) {
            textBuilder.add(key(), String.valueOf(value.duration()));
        }
    },

    SCTE35 {
        @Override
        public void read(CueOutCont.Builder builder, String value) throws PlaylistParserException {
            try {
                Scte35Decoder scte35Decoder = new Scte35Decoder(false);
                SpliceInfoSection spliceInfoSection = scte35Decoder.base64Decode(value);

                Scte35 scte35 = Scte35.builder().cue(value).spliceInfoSection(spliceInfoSection).build();

                builder.scte35(scte35);
            } catch (DecodingException e) {
                throw new PlaylistParserException("scte35 parse failed");
            }
        }

        @Override
        public void write(CueOutCont value, TextBuilder textBuilder) {
            textBuilder.add(key(), value.scte35().cue());
        }
    };

    final static Map<String, CueOutContAttribute> attributeMap = ParserUtils.toMap(values(), Attribute::key);

    static CueOutCont parse(String attributes) throws PlaylistParserException {
        CueOutCont.Builder builder = CueOutCont.builder();
        ParserUtils.readAttributes(attributeMap, attributes, builder);
        return builder.build();
    }
}
