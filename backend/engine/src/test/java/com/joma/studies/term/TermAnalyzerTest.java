package com.joma.studies.term;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TermAnalyzerTest {
    private TermAnalyzer termAnalyzer;

    @Before
    public void setUp() throws Exception {
        termAnalyzer = new TermAnalyzer(new EnglishAnalyzer());
    }

    @Test
    public void whenContainsReadyToUse_shouldReturnItAsIs() throws Exception {
        Assert.assertEquals(
                Arrays.asList("cancer"),
                termAnalyzer.getTerms("cancer")
        );
    }

    @Test
    public void whenContainsDifferentWordsReadyToUse_shouldReturnMapThatLong() throws Exception {
        Assert.assertEquals(
                Arrays.asList("lung", "cancer"),
                termAnalyzer.getTerms("lung cancer")
        );
    }

    @Test
    public void whenContainsSameWordsReadyToUse_shouldReturnOneElementMap() throws Exception {
        Assert.assertEquals(
                Arrays.asList("cancer", "cancer"),
                termAnalyzer.getTerms("cancer cancer")
        );
    }

    @Test
    public void whenContainsStopWords_shouldReturnMapWithoutThem() throws Exception {
        Assert.assertEquals(
                Arrays.asList("lung", "cancer"),
                termAnalyzer.getTerms("lung and cancer")
        );
    }

    @Test
    public void whenContainsWord_shouldReturnStem() throws Exception {
        Assert.assertEquals(
                Arrays.asList("cancer"),
                termAnalyzer.getTerms("cancers")
        );
    }

    @Test
    public void whenContainsDifferentWords_shouldReturnThatManyStems() throws Exception {
        Assert.assertEquals(
                Arrays.asList("lung", "cancer"),
                termAnalyzer.getTerms("lungs cancers")
        );
    }

    @Test
    public void whenContainsSameWords_shouldReturnOneStem() throws Exception {
        Assert.assertEquals(
                Arrays.asList("cancer", "cancer"),
                termAnalyzer.getTerms("cancers cancers")
        );
    }

    @Test
    public void whenContainsStopWords_shouldReturnStemsWithoutThem() throws Exception {
        Assert.assertEquals(
                Arrays.asList("cancer", "symptom"),
                termAnalyzer.getTerms("cancers and their symptoms")
        );
    }

    @Test
    public void whenEdgeCase_shouldReturnProperMap() throws Exception {
        List<String> expected = Arrays.asList(
                "i", "want", "know", "know", "all", "111", "notaword", "n0taw0rd", "cure", "lung", "cancer"
        );
        Assert.assertEquals(
                expected,
                termAnalyzer.getTerms("I WANT  To \tknow if know-it-all 111 #notaword n0taw0rd there is a cure for lung cancer")
        );
    }
}
