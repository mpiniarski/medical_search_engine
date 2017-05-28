package com.joma.studies.measure;

import com.joma.studies.measure.term.TermAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TfMeasureCalculatorTest {

    private TfMeasureCalculator measureCalculator;

    @Before
    public void setUp() throws Exception {
        measureCalculator = new TfMeasureCalculator(new TermAnalyzer(new EnglishAnalyzer()));
    }

    @Test
    public void whenEmpty_shouldReturnEmpty() throws Exception {
        MeasureMap expected = new MeasureMap();
        Assert.assertEquals(expected, measureCalculator.calculate(""));
    }

    @Test
    public void whenContainsReadyToUse_shouldReturnItAsIs() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 1.);

        Assert.assertEquals(expected, measureCalculator.calculate("cancer"));
    }

    @Test
    public void whenContainsDifferentWordsReadyToUse_shouldReturnMapThatLong() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("lung", 1.);
        expected.put("cancer", 1.);

        Assert.assertEquals(expected, measureCalculator.calculate("lung cancer"));
    }

    @Test
    public void whenContainsSameWordsReadyToUse_shouldReturnOneElementMap() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 2.);

        Assert.assertEquals(expected, measureCalculator.calculate("cancer cancer"));
    }

    @Test
    public void whenContainsStopWords_shouldReturnMapWithoutThem() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("lung", 1.);
        expected.put("cancer", 1.);

        Assert.assertEquals(expected, measureCalculator.calculate("lung and cancer"));
    }

    @Test
    public void whenContainsWord_shouldReturnStem() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 1.);

        Assert.assertEquals(expected, measureCalculator.calculate("cancers"));
    }

    @Test
    public void whenContainsDifferentWords_shouldReturnThatManyStems() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("lung", 1.);
        expected.put("cancer", 1.);

        Assert.assertEquals(expected, measureCalculator.calculate("lungs cancers"));
    }

    @Test
    public void whenContainsSameWords_shouldReturnOneStem() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 2.);

        Assert.assertEquals(expected, measureCalculator.calculate("cancers cancers"));
    }

    @Test
    public void whenContainsStopWords_shouldReturnStemsWithoutThem() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 1.);
        expected.put("symptom", 1.);

        Assert.assertEquals(expected, measureCalculator.calculate("cancers and their symptoms"));
    }

    @Test
    public void whenEdgeCase_shouldReturnProperMap() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("i", 1.);
        expected.put("want", 1.);
        expected.put("know", 2.);
        expected.put("all", 1.);
        expected.put("111", 1.);
        expected.put("notaword", 1.);
        expected.put("n0taw0rd", 1.);
        expected.put("cure", 1.);
        expected.put("lung", 1.);
        expected.put("cancer", 1.);

        Assert.assertEquals(expected, measureCalculator.calculate("I WANT  To \tknow if know-it-all 111 #notaword n0taw0rd there is a cure for lung cancer"));
    }
}
