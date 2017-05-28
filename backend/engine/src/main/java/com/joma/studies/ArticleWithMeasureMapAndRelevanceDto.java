package com.joma.studies;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;

public class ArticleWithMeasureMapAndRelevanceDto {
    private final ArticleDto article;
    private final Double relevance;
    private final MeasureMap measureMap;

    private ArticleWithMeasureMapAndRelevanceDto(ArticleDto article, Double relevance, MeasureMap measureMap) {
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

        public Builder withMeasureMap(MeasureMap measureMap) {
            this.measureMap = measureMap;
            return this;
        }

        public ArticleWithMeasureMapAndRelevanceDto build() {
            return new ArticleWithMeasureMapAndRelevanceDto(article, relevance, measureMap);
        }
    }
}
