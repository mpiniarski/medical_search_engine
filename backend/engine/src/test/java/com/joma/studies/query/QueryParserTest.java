package com.joma.studies.query;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.joma.studies.AppModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class QueryParserTest {

    private QueryParser queryParser;

    @Before
    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new AppModule(""));
        queryParser = injector.getInstance(QueryParser.class);
    }

    @Test
    public void whenEmpty_shouldReturnEmpty() throws Exception {
        Assert.assertEquals(new HashMap<>(), queryParser.parse(""));
    }

    @Test
    public void whenContainsReadyToUse_shouldReturnItAsIs() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("cancer", 1);

        Assert.assertEquals(expected, queryParser.parse("cancer"));
    }

    @Test
    public void whenContainsDifferentWordsReadyToUse_shouldReturnMapThatLong() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("lung", 1);
        expected.put("cancer", 1);

        Assert.assertEquals(expected, queryParser.parse("lung cancer"));
    }

    @Test
    public void whenContainsSameWordsReadyToUse_shouldReturnOneElementMap() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("cancer", 2);

        Assert.assertEquals(expected, queryParser.parse("cancer cancer"));
    }

    @Test
    public void whenContainsStopWords_shouldReturnMapWithoutThem() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("lung", 1);
        expected.put("cancer", 1);

        Assert.assertEquals(expected, queryParser.parse("lung and cancer"));
    }

    @Test
    public void whenContainsWord_shouldReturnStem() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("cancer", 1);

        Assert.assertEquals(expected, queryParser.parse("cancers"));
    }

    @Test
    public void whenContainsDifferentWords_shouldReturnThatManyStems() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("lung", 1);
        expected.put("cancer", 1);

        Assert.assertEquals(expected, queryParser.parse("lungs cancers"));
    }

    @Test
    public void whenContainsSameWords_shouldReturnOneStem() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("cancer", 2);

        Assert.assertEquals(expected, queryParser.parse("cancers cancers"));
    }

    @Test
    public void whenContainsStopWords_shouldReturnStemsWithoutThem() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("cancer", 1);
        expected.put("symptom", 1);

        Assert.assertEquals(expected, queryParser.parse("cancers and their symptoms"));
    }

    @Test
    public void whenEdgeCase_shouldReturnProperMap() throws Exception {
        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("i", 1);
        expected.put("want", 1);
        expected.put("know", 2);
        expected.put("all", 1);
        expected.put("111", 1);
        expected.put("notaword", 1);
        expected.put("n0taw0rd", 1);
        expected.put("cure", 1);
        expected.put("lung", 1);
        expected.put("cancer", 1);

        Assert.assertEquals(expected, queryParser.parse("I WANT  To \tknow if know-it-all 111 #notaword n0taw0rd there is a cure for lung cancer"));
    }
}
