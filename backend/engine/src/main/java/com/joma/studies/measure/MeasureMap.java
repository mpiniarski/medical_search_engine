package com.joma.studies.measure;

import java.util.*;

public class MeasureMap extends HashMap<String, Double> implements Map<String, Double> {
    public MeasureMap() {
        super();
    }

    public MeasureMap(Map<? extends String, ? extends Double> map) {
        super(map);
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

    public MeasureMap filter(Set<String> keys) {
        this.keySet().retainAll(keys);
        return this;
    }

    public MeasureMap filter(String... keys) {
        this.filter(new HashSet<>(Arrays.asList(keys)));
        return this;
    }
}
