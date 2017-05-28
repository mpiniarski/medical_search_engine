package com.joma.studies;

import com.joma.studies.measure.MeasureMap;

public class QueryAnalysisDto {
    private final String value;
    private final MeasureMap measureMap;

    private QueryAnalysisDto(String value, MeasureMap measureMap) {
        this.value = value;
        this.measureMap = measureMap;
    }

    public String getValue() {
        return value;
    }

    public MeasureMap getMeasureMap() {
        return measureMap;
    }


    public static final class Builder {
        private String query;
        private MeasureMap measureMap;

        public Builder withQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder withMeasureMap(MeasureMap measureMap) {
            this.measureMap = measureMap;
            return this;
        }

        public QueryAnalysisDto build() {
            return new QueryAnalysisDto(query, measureMap);
        }
    }
}
