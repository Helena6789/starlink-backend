package com.starlink.starlink_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = SatelliteCategoryInfo.Builder.class)

public class SatelliteCategoryInfo {
    @JsonProperty("category")
    private final String category;

    @JsonProperty("satcount")
    private final Integer satCount;

    @JsonProperty("transactionscount")
    private final Integer transactionsCount;

    private SatelliteCategoryInfo(Builder builder) {
        this.category = builder.category;
        this.satCount = builder.satCount;
        this.transactionsCount = builder.transactionsCount;
    }

    public String getCategory() {
        return category;
    }

    public Integer getSatCount() {
        return satCount;
    }

    public Integer getTransactionsCount() {
        return transactionsCount;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty("category")
        private String category;

        @JsonProperty("satcount")
        private Integer satCount;

        @JsonProperty("transactionscount")
        private Integer transactionsCount;

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder satCount(Integer satCount) {
            this.satCount = satCount;
            return this;
        }

        public Builder transactionsCount(Integer transactionsCount) {
            this.transactionsCount = transactionsCount;
            return this;
        }

        public SatelliteCategoryInfo build() {
            return new SatelliteCategoryInfo(this);
        }
    }
}
