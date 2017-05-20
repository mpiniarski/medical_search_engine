package com.joma.studies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class QueryParserTest {

    QueryParser queryParser;

    @Before
    public void setUp() throws Exception {
        queryParser = new QueryParser();
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
}
