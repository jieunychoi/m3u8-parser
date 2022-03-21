package io.lindstrom.m3u8.model;

import org.immutables.value.Value;

/**
 * Cue Out Cont (EXT-X-CUE-OUT-CONT)
 */
@Value.Immutable
public interface CueOutCont {
    Float elapsedTime();

    Float duration();

    Scte35 scte35();

    static Builder builder() {
        return new Builder();
    }

    class Builder extends CueOutContBuilder {}
}
