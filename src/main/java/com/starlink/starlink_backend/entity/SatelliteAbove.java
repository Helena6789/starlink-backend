package com.starlink.starlink_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SatelliteAbove.Builder.class)

public class SatelliteAbove {
    @JsonProperty("satid")
    private final Integer satId;

    @JsonProperty("intDesignator")
    private final String intDesignator;

    @JsonProperty("satname")
    private final String satName;

    @JsonProperty("launchDate")
    private final String launchDate;

    @JsonProperty("satlat")
    private final Double satlat;

    @JsonProperty("satlng")
    private final Double satlng;

    @JsonProperty("satalt")
    private final Double satalt;

    private SatelliteAbove(Builder builder) {
        this.satId = builder.satId;
        this.intDesignator = builder.intDesignator;
        this.satName = builder.satName;
        this.launchDate = builder.launchDate;
        this.satlat = builder.satlat;
        this.satlng = builder.satlng;
        this.satalt = builder.satalt;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("satid")
        private Integer satId;

        @JsonProperty("intDesignator")
        private String intDesignator;

        @JsonProperty("satname")
        private String satName;

        @JsonProperty("launchDate")
        private String launchDate;

        @JsonProperty("satlat")
        private Double satlat;

        @JsonProperty("satlng")
        private Double satlng;

        @JsonProperty("satalt")
        private Double satalt;

        public Builder satId(Integer satId) {
            this.satId = satId;
            return this;
        }

        public Builder intDesignator(String intDesignator) {
            this.intDesignator = intDesignator;
            return this;
        }

        public Builder satName(String satName) {
            this.satName = satName;
            return this;
        }

        public Builder launchDate(String launchDate) {
            this.launchDate = launchDate;
            return this;
        }

        public Builder satlat(Double satlat) {
            this.satlat = satlat;
            return this;
        }

        public Builder satlng(Double satlng) {
            this.satlng = satlng;
            return this;
        }

        public Builder satalt(Double satalt) {
            this.satalt = satalt;
            return this;
        }

        public SatelliteAbove build() {
            return new SatelliteAbove(this);
        }
    }
}
