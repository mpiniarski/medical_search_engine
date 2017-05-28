package com.joma.studies.article.relevance.dto;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;

import java.util.Set;

public class RatedAndMeasuredArticleDto {
    private final ArticleDto article;
    private final Double relevance;
    private final MeasureMap measureMap;

    private RatedAndMeasuredArticleDto(ArticleDto article, Double relevance, MeasureMap measureMap) {
        this.article = article;
        this.relevance = relevance;
        this.measureMap = measureMap;
    }

    public ArticleDto getArticle() {
        return article;
    }

    public Double getRelevance() {
        return relevance;
    }

    public MeasureMap getMeasureMap() {
        return measureMap;
    }

    public static final class Builder {
        private ArticleDto article;
        private Double relevance;
        private MeasureMap measureMap;

        public Builder withArticle(ArticleDto article) {
            this.article = article;
            return this;
        }

        public Builder withRelevance(Double relevance) {
            this.relevance = relevance;
            return this;
        }

        public Builder withMeasureMapFiltered(MeasureMap measureMap, Set<String> queryWords) {
            this.measureMap = measureMap.filter(queryWords);
            return this;
        }

        public RatedAndMeasuredArticleDto build() {
            return new RatedAndMeasuredArticleDto(article, relevance, measureMap);
        }
    }
}
