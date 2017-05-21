package com.joma.studies;

import com.google.inject.AbstractModule;
import com.joma.studies.article.importance.ArticleImportanceSorter;
import com.joma.studies.article.importance.TfArticleImportanceSorter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class AppModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Analyzer.class).toInstance(new StandardAnalyzer());
        bind(ArticleImportanceSorter.class).to(TfArticleImportanceSorter.class);
    }
}
