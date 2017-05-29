package com.joma.studies;

import com.joma.studies.measure.MeasureMap;

import java.util.List;
import java.util.Map;

public class DecisionSupportQueryDto {
    private String queryText;
    private Map<String, Integer> weights;
    private List<MeasureMap> positiveArticleMeasureMaps;
    private List<MeasureMap> negativeArticleMeasureMaps;

    //for deserialization
    public DecisionSupportQueryDto() {
    }

    private DecisionSupportQueryDto(String queryText,
                                    Map<String, Integer> weights,
                                    List<MeasureMap> positiveArticleMeasureMaps,
                                    List<MeasureMap> negativeArticleMeasureMaps) {
        this.queryText = queryText;
        this.weights = weights;
        this.positiveArticleMeasureMaps = positiveArticleMeasureMaps;
        this.negativeArticleMeasureMaps = negativeArticleMeasureMaps;
    }

    public String getQueryText() {
        return queryText;
    }

    public Map<String, Integer> getWeights() {
        return weights;
    }

    public List<MeasureMap> getPositiveArticleMeasureMaps() {
        return positiveArticleMeasureMaps;
    }

    public List<MeasureMap> getNegativeArticleMeasureMaps() {
        return negativeArticleMeasureMaps;
    }


    public static final class Builder {
        private String queryText;
        private Map<String, Integer> weights;
        private List<MeasureMap> positiveArticleMeasureMaps;
        private List<MeasureMap> negativeArticleMeasureMaps;

        public Builder withQueryText(String queryText) {
            this.queryText = queryText;
            return this;
        }

        public Builder withWeights(Map<String, Integer> weights) {
            this.weights = weights;
            return this;
        }

        public Builder withPositiveArticleMeasureMaps(List<MeasureMap> positiveArticleMeasureMaps) {
            this.positiveArticleMeasureMaps = positiveArticleMeasureMaps;
            return this;
        }

        public Builder withNegativeArticleMeasureMaps(List<MeasureMap> negativeArticleMeasureMaps) {
            this.negativeArticleMeasureMaps = negativeArticleMeasureMaps;
            return this;
        }

        public DecisionSupportQueryDto build() {
            return new DecisionSupportQueryDto(queryText, weights, positiveArticleMeasureMaps, negativeArticleMeasureMaps);
        }
    }
}
