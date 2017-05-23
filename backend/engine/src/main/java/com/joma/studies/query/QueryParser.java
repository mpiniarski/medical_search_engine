package com.joma.studies.query;

import com.google.inject.Inject;
import com.joma.studies.query.exception.UnableToParseQueryException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueryParser {
    private final Analyzer analyzer;

    @Inject
    public QueryParser(Analyzer analyzer) {
        this.analyzer = analyzer;
    }

    public Map<String, Integer> parse(String query) throws UnableToParseQueryException {
        try {
            HashMap<String, Integer> result = new HashMap<>();
            TokenStream tokenStream =
                    analyzer.tokenStream(null, query);
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                if (result.containsKey(term)) {
                    Integer value = result.get(term);
                    result.replace(term, value, ++value);
                } else {
                    result.put(charTermAttribute.toString(), 1);
                }
            }
            tokenStream.end();
            tokenStream.close();
            return result;
        } catch (IOException exception) {
            throw new UnableToParseQueryException(query, exception);
        }

    }
}
