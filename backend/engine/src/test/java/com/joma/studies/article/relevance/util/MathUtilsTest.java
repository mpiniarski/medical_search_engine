package com.joma.studies.article.relevance.util;

import com.joma.studies.measure.MeasureMap;
import org.junit.Assert;
import org.junit.Test;

public class MathUtilsTest {

    private static final double DELTA = 10e-5;

    @Test(expected = IllegalArgumentException.class)
    public void whenEmpty_shouldThrowIllegalArgumentException() throws Exception {
        MathUtils.cosineMeasure(new MeasureMap(), new MeasureMap());
    }

    @Test
    public void whenContainsDifferentWords_shouldReturnArcCosZero() throws Exception {
        MeasureMap query = new MeasureMap();
        query.put("cancer", 1.);
        MeasureMap article = new MeasureMap();
        article.put("breasts", 1.);
        double distance = MathUtils.cosineMeasure(query, article);
        Assert.assertEquals(0.0, distance, DELTA);
    }

    @Test
    public void whenContainsSameWords_shouldReturnArcCosOne() throws Exception {
        MeasureMap query = new MeasureMap();
        query.put("cancer", 1.);
        MeasureMap article = new MeasureMap();
        article.put("cancer", 1.);
        double distance = MathUtils.cosineMeasure(query, article);
        Assert.assertEquals(1.0, distance, DELTA);
    }

    @Test
    public void whenContainsSamePairAndDifferentPair_shouldReturnArcCosHalf() throws Exception {
        MeasureMap query = new MeasureMap();
        query.put("breast", 1.);
        query.put("cancer", 1.);
        MeasureMap article = new MeasureMap();
        article.put("lung", 1.);
        article.put("cancer", 1.);
        double distance = MathUtils.cosineMeasure(query, article);
        Assert.assertEquals(0.5, distance, DELTA);
    }

    @Test
    public void whenContainsSameWordsWithDifferentWeight_shouldReturnArcCosOne() throws Exception {
        MeasureMap query = new MeasureMap();
        query.put("cancer", 2.);
        MeasureMap article = new MeasureMap();
        article.put("cancer", 3.);
        double distance = MathUtils.cosineMeasure(query, article);
        Assert.assertEquals(1., distance, DELTA);
    }

    @Test
    public void whenContainsSamePairAndDifferentPairWithDifferentWeight_shouldReturnProperArcCos() throws Exception {
        MeasureMap query = new MeasureMap();
        query.put("breast", 2.);
        query.put("cancer", 3.);
        MeasureMap article = new MeasureMap();
        article.put("lung", 4.);
        article.put("cancer", 5.);
        double distance = MathUtils.cosineMeasure(query, article);
        Assert.assertEquals(15 / (Math.sqrt(533)), distance, DELTA);
    }

    @Test
    public void whenContainsGivenPairs_shouldReturnProperArcCos() throws Exception {
        MeasureMap query = new MeasureMap();
        query.put("lung", 7.);
        query.put("cancer", 1.);
        MeasureMap article = new MeasureMap();
        article.put("lung", 5.);
        article.put("cancer", 5.);
        double distance = MathUtils.cosineMeasure(query, article);
        Assert.assertEquals(0.8, distance, DELTA);
    }
}
