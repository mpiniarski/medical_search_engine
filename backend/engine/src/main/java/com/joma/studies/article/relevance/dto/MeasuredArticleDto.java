package com.joma.studies.article.relevance.dto;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;

public class MeasuredArticleDto {
    private final ArticleDto articleDto;
    private final MeasureMap measureMap;

    private MeasuredArticleDto(ArticleDto articleDto, MeasureMap measureMap) {
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

        public MeasuredArticleDto build() {
            return new MeasuredArticleDto(articleDto, measureMap);
        }
    }

//    public static final class FromMeasureMapsBuilder {
//        private ArticleDto articleDto;
//        private MeasureMap titleMeasureMap;
//        private MeasureMap abstractMeasureMap;
//
//        public FromMeasureMapsBuilder withArticleDto(ArticleDto articleDto) {
//            this.articleDto = articleDto;
//            return this;
//        }
//
//        public FromMeasureMapsBuilder withTitleMeasureMap(MeasureMap titleMeasureMap, int titleWage) {
//            this.measureMap = titleMeasureMap;
//            return this;
//        }
//
//        public MeasuredArticleDto build() {
//            return new MeasuredArticleDto(articleDto, measureMap);
//        }
//    }
}
