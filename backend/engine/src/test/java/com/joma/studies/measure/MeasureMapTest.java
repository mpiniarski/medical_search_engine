package com.joma.studies.measure;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MeasureMapTest {

    @Test
    public void shouldRiseWeights() throws Exception {
        //Given
        MeasureMap original= new MeasureMap();
        original.put("lung", 2.0);
        original.put("cancer", 3.0);

        Map<String, Integer> weights = new HashMap<>();
        weights.put("lung", 3);
        weights.put("cancer", 1);

        //When
        original.riseWeights(weights);

        //Then
        MeasureMap expected = new MeasureMap();
        expected.put("lung", 6.0);
        expected.put("cancer", 3.0);
        assertEquals(expected, original);
    }

    @Test
    public void shouldNotChange_WhenWeightOfTermNotFound() throws Exception {
        //Given
        MeasureMap original= new MeasureMap();
        original.put("lung", 2.0);
        original.put("cancer", 3.0);

        Map<String, Integer> weights = new HashMap<>();

        //When
        original.riseWeights(weights);

        //Then
        MeasureMap expected = new MeasureMap();
        expected.put("lung", 2.0);
        expected.put("cancer", 3.0);
        assertEquals(expected, original);
    }

    @Test
    public void shouldNormalize() throws Exception {
        //Given
        MeasureMap result = new MeasureMap();
        result.put("the", 1.);
        result.put("game", 2.);
        result.put("of", 2.);
        result.put("life", 1.);
        result.put("is", 1.);
        result.put("a", 1.);
        result.put("everlasting", 1.);
        result.put("learnig", 1.);

        //When
        result.normalize();

        //Then
        MeasureMap expected = new MeasureMap();
        expected.put("the", 0.1);
        expected.put("game", 0.2);
        expected.put("of", 0.2);
        expected.put("life", 0.1);
        expected.put("is", 0.1);
        expected.put("a", 0.1);
        expected.put("everlasting", 0.1);
        expected.put("learnig", 0.1);

        expected.forEach((key, value) ->
                assertEquals(value, result.get(key))
        );
    }

    @Test
    public void shouldFilter() throws Exception {
        //Given
        MeasureMap result = new MeasureMap();
        result.put("the", 1.);
        result.put("game", 2.);
        result.put("of", 2.);
        result.put("life", 1.);
        result.put("is", 1.);
        result.put("a", 1.);
        result.put("everlasting", 1.);
        result.put("learnig", 1.);

        //When
        result.filter("the", "game");

        //Then
        MeasureMap expected = new MeasureMap();
        expected.put("the", 1.);
        expected.put("game", 2.);

        assertEquals(expected, result);


    }
}
