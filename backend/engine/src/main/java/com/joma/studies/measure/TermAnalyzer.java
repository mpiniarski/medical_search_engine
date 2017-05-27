package com.joma.studies.measure;

import com.joma.studies.measure.exception.MeasureCalculationException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TermAnalyzer {

    private final Analyzer analyzer;

    @Autowired
    public TermAnalyzer(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public List<String> getTermList(String text) {
        try {
            List<String> result = new ArrayList<>();
            TokenStream tokenStream = analyzer.tokenStream(null, text);
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                result.add(charTermAttribute.toString());
            }
            tokenStream.end();
            tokenStream.close();
            return result;
        } catch (IOException exception) {
            throw new MeasureCalculationException(text, exception);
        }
    }
}
