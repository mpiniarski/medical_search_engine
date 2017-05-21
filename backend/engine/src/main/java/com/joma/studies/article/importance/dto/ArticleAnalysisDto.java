package com.joma.studies.article.importance.dto;

import com.joma.studies.article.dto.ArticleDto;

import java.util.Map;

public class ArticleAnalysisDto {
    private final ArticleDto articleDto;
    private final Map<String, Integer> termFrequency;

    private ArticleAnalysisDto(ArticleDto articleDto, Map<String, Integer> termFrequency) {
        this.articleDto = articleDto;
        this.termFrequency = termFrequency;
    }

    public ArticleDto getArticleDto() {
        return articleDto;
    }

    public Map<String, Integer> getTermFrequency() {
        return termFrequency;
    }


    public static final class Builder {
        private ArticleDto articleDto;
        private Map<String, Integer> termFrequency;

        public Builder withArticleDto(ArticleDto articleDto) {
            this.articleDto = articleDto;
            return this;
        }

        public Builder withTermFrequency(Map<String, Integer> termFrequency) {
            this.termFrequency = termFrequency;
            return this;
        }

        public ArticleAnalysisDto build() {
            return new ArticleAnalysisDto(articleDto, termFrequency);
        }
    }
}
