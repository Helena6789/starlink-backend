package com.starlink.starlink_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SatelliteObserver.Builder.class)

public class SatelliteObserver {
    @JsonProperty("info")
    private final SatelliteCategoryInfo info;

    @JsonProperty("above")
    private final List<SatelliteAbove> above;

    private SatelliteObserver(Builder builder) {
        this.info = builder.info;
        this.above = builder.above;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("info")
        private SatelliteCategoryInfo info;

        @JsonProperty("above")
        private List<SatelliteAbove> above;

        public Builder info(SatelliteCategoryInfo info) {
            this.info = info;
            return this;
        }

        public Builder above(List<SatelliteAbove> above) {
            this.above = above;
            return this;
        }

        public SatelliteObserver build() {
            return new SatelliteObserver(this);
        }
    }
}
