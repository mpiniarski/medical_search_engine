package com.joma.studies.article.relevance.dto;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;

public class ArticleAnalysisDto {
    private final ArticleDto articleDto;
    private final MeasureMap measureMap;

    private ArticleAnalysisDto(ArticleDto articleDto, MeasureMap measureMap) {
        this.articleDto = articleDto;
        this.measureMap = measureMap;
    }

    public ArticleDto getArticleDto() {
        return articleDto;
    }

    public MeasureMap getMeasureMap() {
        return measureMap;
    }


    public static final class Builder {
        private ArticleDto articleDto;
        private MeasureMap measureMap;

        public Builder withArticleDto(ArticleDto articleDto) {
            this.articleDto = articleDto;
            return this;
        }

        public Builder withMeasureMap(MeasureMap measureMap) {
            this.measureMap = measureMap;
            return this;
        }

        public ArticleAnalysisDto build() {
            return new ArticleAnalysisDto(articleDto, measureMap);
        }
    }
}
