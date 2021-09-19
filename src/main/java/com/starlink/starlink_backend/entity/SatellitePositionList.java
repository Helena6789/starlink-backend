package com.starlink.starlink_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SatellitePositionList.Builder.class)

public class SatellitePositionList {
    @JsonProperty("info")
    private final SatellitePositionInfo info;

    @JsonProperty("positions")
    private final List<SatellitePosition> positions;

    private SatellitePositionList(Builder builder) {
        this.info = builder.info;
        this.positions = builder.positions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("info")
        private SatellitePositionInfo info;

        @JsonProperty("positions")
        private List<SatellitePosition> positions;

        public Builder info(SatellitePositionInfo info) {
            this.info = info;
            return this;
        }

        public Builder positions(List<SatellitePosition> positions) {
            this.positions = positions;
            return this;
        }

        public SatellitePositionList build() {
            return new SatellitePositionList(this);
        }
    }
}
