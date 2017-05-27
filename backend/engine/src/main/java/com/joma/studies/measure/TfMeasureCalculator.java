package com.joma.studies.measure;

import com.joma.studies.measure.exception.MeasureCalculationException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TfMeasureCalculator implements SimpleMeasureCalculator {
    private final Analyzer analyzer;

    @Autowired
    public TfMeasureCalculator(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    @Override
    public MeasureMap calculate(String text) throws MeasureCalculationException {
        try {
            MeasureMap result = new MeasureMap();
            TokenStream tokenStream =
                    analyzer.tokenStream(null, text);
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                if (result.containsKey(term)) {
                    Double value = result.get(term);
                    result.replace(term, value, value + 1.0);
                } else {
                    result.put(charTermAttribute.toString(), 1.0);
                }
            }
            tokenStream.end();
            tokenStream.close();
            return result;
        } catch (IOException exception) {
            throw new MeasureCalculationException(text, exception);
        }

    }
}
