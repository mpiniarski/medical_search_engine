package com.joma.studies;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.joma.studies.article.importance.ArticleImportanceSorter;
import com.joma.studies.article.importance.TfArticleImportanceSorter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class AppModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Analyzer.class).toInstance(new EnglishAnalyzer());
        bind(ArticleImportanceSorter.class).to(TfArticleImportanceSorter.class);
    }

    @Provides
    public Directory provideDirectory() throws IOException {
        return FSDirectory.open(
                Paths.get("/home/marcin/Projekty/medical_search_engine/index")
        );
    }
}
