package com.joma.studies;

import com.google.inject.AbstractModule;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

public class AppModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Analyzer.class).toInstance(new StandardAnalyzer());
    }
}
