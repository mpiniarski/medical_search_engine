package com.joma.studies;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

@Configuration
public class EngineConfig {
    private final EngineConfigProvider engineConfigProvider;

    @Autowired
    public EngineConfig(EngineConfigProvider engineConfigProvider) {
        this.engineConfigProvider = engineConfigProvider;
    }

    @Bean
    public Analyzer provideAnalyzer() {
        return new EnglishAnalyzer();
    }

    @Bean
    public Directory provideDirectory() throws IOException {
        return FSDirectory.open(Paths.get(engineConfigProvider.getIndexDirectory()));
    }
}
