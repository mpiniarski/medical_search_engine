package com.joma.studies.config

import com.google.inject.Guice
import com.google.inject.Injector
import com.joma.studies.AppModule
import com.joma.studies.SearchEngine
import com.joma.studies.article.importance.TfArticleImportanceSorter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
open class AppConfig {
    @Bean
    open fun provideGuiceInjector(): Injector {
        return Guice.createInjector(AppModule())
    }

    @Bean
    open fun provideSearchEngine(injector: Injector): SearchEngine {
        return injector.getInstance(SearchEngine::class.java)
    }

    @Bean
    open fun provideTfArticleImportanceSorter(injector: Injector): TfArticleImportanceSorter {
        return injector.getInstance(TfArticleImportanceSorter::class.java)
    }
}

