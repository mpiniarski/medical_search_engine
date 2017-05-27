package com.joma.studies.measure;

import java.util.HashMap;
import java.util.Map;

public class MeasureMap extends HashMap<String, Double> implements Map<String,Double> {
    public MeasureMap() {
        super();
    }

    public MeasureMap(Map<? extends String, ? extends Double> map) {
        super(map);
    }

    public MeasureMap(int i, float v) {
        super(i, v);
    }

    public MeasureMap(int i) {
        super(i);
    }
}
