package com.joma.studies.measure;

import java.util.*;

public class MeasureMap extends HashMap<String, Double> implements Map<String, Double> {
    public MeasureMap() {
        super();
    }

    public MeasureMap(Map<? extends String, ? extends Double> map) {
        super(map);
    }

    public MeasureMap riseWeights(Map<String, Integer> weights) {
        this.replaceAll((key, value) ->
                value * weights.getOrDefault(key, 1)
        );
        return this;
    }

    public MeasureMap rocchioTransform(Double originalMapWeight,
                                       List<MeasureMap> positiveMaps,
                                       Double positiveMapsWeight,
                                       List<MeasureMap> negativeMaps,
                                       Double negativeMapsWeight) {
        this.replaceAll((key, value) ->
                originalMapWeight * value
                        + calculateRocchioFactor(key, positiveMaps, positiveMapsWeight)
                        - calculateRocchioFactor(key, negativeMaps, negativeMapsWeight)
        );
        return this;
    }

    private double calculateRocchioFactor(String key, List<MeasureMap> maps, Double weight) {
        if (maps.size() == 0) {
            return 0;
        }
        double sum = maps.stream()
                .mapToDouble(map -> map.getOrDefault(key, 0.0))
                .sum();
        return weight * sum / maps.size();
    }

    public MeasureMap filter(Set<String> keys) {
        this.keySet().retainAll(keys);
        return this;
    }

    public MeasureMap filter(String... keys) {
        this.filter(new HashSet<>(Arrays.asList(keys)));
        return this;
    }

    public MeasureMap normalize() {
        double sum = this.entrySet().stream()
                .mapToDouble(Map.Entry::getValue)
                .sum();
        this.forEach((key, value) ->
                this.replace(key, value, value / sum)
        );
        return this;
    }
}
