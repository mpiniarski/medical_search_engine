package com.joma.studies.measure;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TfSquaredMeasureCalculatorTest {

    private TfSquaredMeasureCalculator measureCalculator;

    @Before
    public void setUp() throws Exception {
        measureCalculator = new TfSquaredMeasureCalculator(new TfMeasureCalculator());
    }

    @Test
    public void shouldCountTermsAndSquare() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 4.0);
        expected.put("lung", 1.0);

        assertEquals(
                expected,
                measureCalculator.calculate(Arrays.asList("cancer", "lung", "cancer"))
        );
    }
}
