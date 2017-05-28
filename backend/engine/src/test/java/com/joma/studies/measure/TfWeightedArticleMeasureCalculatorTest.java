package com.joma.studies.measure;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TfWeightedArticleMeasureCalculatorTest {

    private TfWeightedArticleMeasureCalculator measureCalculator;

    @Before
    public void setUp() throws Exception {
        measureCalculator = new TfWeightedArticleMeasureCalculator(
                new TfWeightedMeasureCalculator(new TfMeasureCalculator())
        );
    }

    @Test
    public void shouldCountTermsAndWeight() throws Exception {
        MeasureMap expected = new MeasureMap();
        expected.put("cancer", 10.0);
        expected.put("lung", 1.0);

        assertEquals(
                expected,
                measureCalculator.calculate(
                        Arrays.asList("cancer", "cancer"), 5.0,
                        Arrays.asList("lung"), 1.0
                )
        );
    }

}
