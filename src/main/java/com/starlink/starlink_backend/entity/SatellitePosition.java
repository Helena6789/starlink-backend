package com.starlink.starlink_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SatellitePosition.Builder.class)

public class SatellitePosition {
    @JsonProperty("satlatitude")
    private final Double satLatitude;

    @JsonProperty("satlongitude")
    private final Double satLongitude;

    @JsonProperty("azimuth")
    private final Double azimuth;

    @JsonProperty("elevation")
    private final Double elevation;

    @JsonProperty("ra")
    private final Double ra;

    @JsonProperty("dec")
    private final Double dec;

    @JsonProperty("timestamp")
    private final Integer timestamp;

    private SatellitePosition(Builder builder) {
        this.satLatitude = builder.satLatitude;
        this.satLongitude = builder.satLongitude;
        this.azimuth = builder.azimuth;
        this.elevation = builder.elevation;
        this.ra = builder.ra;
        this.dec = builder.dec;
        this.timestamp = builder.timestamp;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("satlatitude")
        private Double satLatitude;

        @JsonProperty("satlongitude")
        private Double satLongitude;

        @JsonProperty("azimuth")
        private Double azimuth;

        @JsonProperty("elevation")
        private Double elevation;

        @JsonProperty("ra")
        private Double ra;

        @JsonProperty("dec")
        private Double dec;

        @JsonProperty("timestamp")
        private Integer timestamp;

        public Builder satLatitude(Double satLatitude) {
            this.satLatitude = satLatitude;
            return this;
        }

        public Builder satLongitude(Double satLongitude) {
            this.satLongitude = satLongitude;
            return this;
        }

        public Builder azimuth(Double azimuth) {
            this.azimuth = azimuth;
            return this;
        }

        public Builder elevation(Double elevation) {
            this.elevation = elevation;
            return this;
        }

        public Builder ra(Double ra) {
            this.ra = ra;
            return this;
        }

        public Builder dec(Double dec) {
            this.dec = dec;
            return this;
        }

        public Builder timestamp(Integer timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public SatellitePosition build() {
            return new SatellitePosition(this);
        }
    }
}

