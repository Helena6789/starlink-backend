package com.starlink.starlink_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SatellitePositionInfo.Builder.class)

public class SatellitePositionInfo {
    @JsonProperty("satname")
    private final String satName;

    @JsonProperty("satid")
    private final Integer satId;

    @JsonProperty("transactionscount")
    private final Integer transactionsCount;

    private SatellitePositionInfo(Builder builder) {
        this.satName = builder.satName;
        this.satId = builder.satId;
        this.transactionsCount = builder.transactionsCount;
    }

    public String getSatName() {
        return satName;
    }

    public Integer getSatId() {
        return satId;
    }

    public Integer getTransactionsCount() {
        return transactionsCount;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("satname")
        private String satName;

        @JsonProperty("satid")
        private Integer satId;

        @JsonProperty("transactionscount")
        private Integer transactionsCount;

        public Builder satName(String satName) {
            this.satName = satName;
            return this;
        }

        public Builder satId(Integer satId) {
            this.satId = satId;
            return this;
        }

        public Builder transactionsCount(Integer transactionsCount) {
            this.transactionsCount = transactionsCount;
            return this;
        }

        public SatellitePositionInfo build() {
            return new SatellitePositionInfo(this);
        }
    }
}
