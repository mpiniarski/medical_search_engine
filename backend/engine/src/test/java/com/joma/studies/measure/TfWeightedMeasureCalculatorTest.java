package com.joma.studies.measure;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TfWeightedMeasureCalculatorTest {

    private TfWeightedMeasureCalculator measureCalculator;

    @Before
    public void setUp() throws Exception {
        measureCalculator = new TfWeightedMeasureCalculator(new TfMeasureCalculator());
    }

    @Test
    public void shouldCountTermsAndWeight() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 4.0);
        expected.put("lung", 2.0);

        assertEquals(
                expected,
                measureCalculator.calculate(Arrays.asList("cancer", "lung", "cancer"), 2.0)
        );
    }

}
