package com.joma.studies;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.joma.studies.config.ConfigProvider;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class AppModule extends AbstractModule {

    private final ConfigProvider configProvider;

    public AppModule(ConfigProvider configProvider) {
        this.configProvider = configProvider;
    }

    @Override
    protected void configure() {
        bind(ConfigProvider.class).toInstance(configProvider);
        bind(Analyzer.class).toInstance(new EnglishAnalyzer());
    }

    @Provides
    public Directory provideDirectory(ConfigProvider configProvider) throws IOException {
        return FSDirectory.open(
                Paths.get(configProvider.getIndexDirectory())
        );
    }
}
