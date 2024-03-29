package com.joma.studies.measure;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TfMeasureCalculatorTest {

    private TfMeasureCalculator measureCalculator;

    @Before
    public void setUp() throws Exception {
        measureCalculator = new TfMeasureCalculator();
    }

    @Test
    public void shouldCountTerms() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 2.0);
        expected.put("lung", 1.0);

        assertEquals(
                expected,
                measureCalculator.calculate(Arrays.asList("cancer", "lung", "cancer"))
        );
    }
}
