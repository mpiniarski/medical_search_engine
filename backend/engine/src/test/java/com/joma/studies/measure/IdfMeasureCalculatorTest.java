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
//
//    @Test
//    public void whenContainsDifferentWordsReadyToUse_shouldReturnMapThatLong() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("lung", 1.);
//        expected.put("cancer", 1.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("lung cancer"));
//    }
//
//    @Test
//    public void whenContainsSameWordsReadyToUse_shouldReturnOneElementMap() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("cancer", 2.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("cancer cancer"));
//    }
//
//    @Test
//    public void whenContainsStopWords_shouldReturnMapWithoutThem() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("lung", 1.);
//        expected.put("cancer", 1.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("lung and cancer"));
//    }
//
//    @Test
//    public void whenContainsWord_shouldReturnStem() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("cancer", 1.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("cancers"));
//    }
//
//    @Test
//    public void whenContainsDifferentWords_shouldReturnThatManyStems() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("lung", 1.);
//        expected.put("cancer", 1.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("lungs cancers"));
//    }
//
//    @Test
//    public void whenContainsSameWords_shouldReturnOneStem() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("cancer", 2.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("cancers cancers"));
//    }
//
//    @Test
//    public void whenContainsStopWords_shouldReturnStemsWithoutThem() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("cancer", 1.);
//        expected.put("symptom", 1.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("cancers and their symptoms"));
//    }
//
//    @Test
//    public void whenEdgeCase_shouldReturnProperMap() throws Exception {
//        MeasureMap expected = new MeasureMap();
//        expected.put("i", 1.);
//        expected.put("want", 1.);
//        expected.put("know", 2.);
//        expected.put("all", 1.);
//        expected.put("111", 1.);
//        expected.put("notaword", 1.);
//        expected.put("n0taw0rd", 1.);
//        expected.put("cure", 1.);
//        expected.put("lung", 1.);
//        expected.put("cancer", 1.);
//
//        Assert.assertEquals(expected, measureCalculator.calculate("I WANT  To \tknow if know-it-all 111 #notaword n0taw0rd there is a cure for lung cancer"));
//    }
}
