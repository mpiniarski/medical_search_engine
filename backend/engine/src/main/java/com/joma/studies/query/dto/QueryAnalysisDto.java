package com.joma.studies.query.dto;

import java.util.Map;

public class QueryAnalysisDto {
    private final String query;
    private final Map<String, Integer> termFrequency;

    private QueryAnalysisDto(String query, Map<String, Integer> termFrequency) {
        this.query = query;
        this.termFrequency = termFrequency;
    }

    public String getQuery() {
        return query;
    }

    public Map<String, Integer> getTermFrequency() {
        return termFrequency;
    }


    public static final class Builder {
        private String query;
        private Map<String, Integer> termFrequency;

        public Builder withQuery(String query) {
            this.query = query;
            return this;
        }

        public Builder withTermFrequency(Map<String, Integer> termFrequency) {
            this.termFrequency = termFrequency;
            return this;
        }

        public QueryAnalysisDto build() {
            return new QueryAnalysisDto(query, termFrequency);
        }
    }
}
