package com.joma.studies.measure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeasureMapTest {
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
