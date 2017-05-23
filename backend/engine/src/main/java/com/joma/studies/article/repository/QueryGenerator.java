package com.joma.studies.article.repository;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.util.Set;

public class QueryGenerator {

    private static final String TITLE = "title";
    private static final String ABSTRACT = "abstractText";

    Query generate(Set<String> words) {
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        words.forEach(word -> {
            builder.add(new TermQuery(new Term(TITLE, word)), BooleanClause.Occur.SHOULD);
            builder.add(new TermQuery(new Term(ABSTRACT, word)), BooleanClause.Occur.SHOULD);
        });
        return new ConstantScoreQuery(
                builder.build()
        );
    }
}
