package com.joma.studies.article.importance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MathUtilsTest {

    private static final double DELTA = 10e-5;
    private MathUtils mathUtils;

    @Before
    public void setUp() throws Exception {
        mathUtils = new MathUtils();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmpty_shouldThrowIllegalArgumentException() throws Exception {
        mathUtils.angle(new HashMap<>(), new HashMap<>());
    }

    @Test
    public void whenContainsDifferentWords_shouldReturnArcCosZero() throws Exception {
        Map<String, Integer> query = new HashMap<>();
        query.put("cancer", 1);
        Map<String, Integer> article = new HashMap<>();
        article.put("breasts", 1);
        double distance = mathUtils.angle(query, article);
        Assert.assertEquals(Math.acos(0.0), distance, DELTA);
    }

    @Test
    public void whenContainsSameWords_shouldReturnArcCosOne() throws Exception {
        Map<String, Integer> query = new HashMap<>();
        query.put("cancer", 1);
        Map<String, Integer> article = new HashMap<>();
        article.put("cancer", 1);
        double distance = mathUtils.angle(query, article);
        Assert.assertEquals(Math.acos(1.0), distance, DELTA);
    }

    @Test
    public void whenContainsSamePairAndDifferentPair_shouldReturnArcCosHalf() throws Exception {
        Map<String, Integer> query = new HashMap<>();
        query.put("breast", 1);
        query.put("cancer", 1);
        Map<String, Integer> article = new HashMap<>();
        article.put("lung", 1);
        article.put("cancer", 1);
        double distance = mathUtils.angle(query, article);
        Assert.assertEquals(Math.acos(0.5), distance, DELTA);
    }

    @Test
    public void whenContainsSameWordsWithDifferentWeight_shouldReturnArcCosOne() throws Exception {
        Map<String, Integer> query = new HashMap<>();
        query.put("cancer", 2);
        Map<String, Integer> article = new HashMap<>();
        article.put("cancer", 3);
        double distance = mathUtils.angle(query, article);
        Assert.assertEquals(Math.acos(1), distance, DELTA);
    }

    @Test
    public void whenContainsSamePairAndDifferentPairWithDifferentWeight_shouldReturnProperArcCos() throws Exception {
        Map<String, Integer> query = new HashMap<>();
        query.put("breast", 2);
        query.put("cancer", 3);
        Map<String, Integer> article = new HashMap<>();
        article.put("lung", 4);
        article.put("cancer", 5);
        double distance = mathUtils.angle(query, article);
        Assert.assertEquals(Math.acos(15/(Math.sqrt(533))), distance, DELTA);
    }

    @Test
    public void whenContainsGivenPairs_shouldReturnProperArcCos() throws Exception {
        Map<String, Integer> query = new HashMap<>();
        query.put("lung", 7);
        query.put("cancer", 1);
        Map<String, Integer> article = new HashMap<>();
        article.put("lung", 5);
        article.put("cancer", 5);
        double distance = mathUtils.angle(query, article);
        Assert.assertEquals(Math.acos(0.8), distance, DELTA);
    }
}
