package com.joma.studies.measure;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IdfMeasureCalculatorTest {
    private static final double DELTA = 10e-4;

    private IdfMeasureCalculator measureCalculator;

    @Before
    public void setUp() throws Exception {
        measureCalculator = new IdfMeasureCalculator(new TermAnalyzer(new EnglishAnalyzer()));
    }

    @Test
    public void whenEmpty_shouldReturnEmpty() throws Exception {
        MeasureMap expected = new MeasureMap();
        assertEquals(expected, measureCalculator.calculate(Collections.emptyList()));
    }

    @Test
    public void testHardExample() throws Exception {
        List<String> documents = Arrays.asList(
                "The game of life is a game of everlasting learning",
                "The unexamined life is not worth living",
                "Never stop learning"
        );

        MeasureMap expectedMeasureMap = new MeasureMap();
        expectedMeasureMap.put("never", 2.098726209);
        expectedMeasureMap.put("game", 2.098726209);
        expectedMeasureMap.put("life", 1.405507153);
        expectedMeasureMap.put("everlast", 2.098726209);
        expectedMeasureMap.put("learn", 1.405507153);
        expectedMeasureMap.put("unexamin", 2.098726209);
        expectedMeasureMap.put("worth", 2.098726209);
        expectedMeasureMap.put("live", 2.098726209);
        expectedMeasureMap.put("stop", 2.098726209);

        MeasureMap resultMeasureMap = measureCalculator.calculate(documents);

        resultMeasureMap.forEach((key, value) ->
                assertEquals(value, expectedMeasureMap.get(key), DELTA)
        );

    }
}
