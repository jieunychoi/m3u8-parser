package io.lindstrom.m3u8.model;

import com.nfl.scte35.decoder.model.SpliceInfoSection;
import org.immutables.value.Value;

import java.util.Optional;

/**
 * scte-35 (EXT-OATCLS-SCTE35:)
 */
@Value.Immutable
public interface Scte35 {
    String cue();

    Optional<SpliceInfoSection> spliceInfoSection();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends Scte35Builder {}
}
