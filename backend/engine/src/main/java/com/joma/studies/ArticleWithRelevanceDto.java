package com.joma.studies;

import com.joma.studies.article.dto.ArticleDto;

public class ArticleWithRelevanceDto {
    private final ArticleDto article;
    private final Double relevance;

    private ArticleWithRelevanceDto(ArticleDto article, Double relevance) {
        this.article = article;
        this.relevance = relevance;
    }

    public ArticleDto getArticle() {
        return article;
    }

    public Double getRelevance() {
        return relevance;
    }


    public static final class Builder {
        private ArticleDto article;
        private Double relevance;

        public Builder withArticle(ArticleDto article) {
            this.article = article;
            return this;
        }

        public Builder withRelevance(Double relevance) {
            this.relevance = relevance;
            return this;
        }

        public ArticleWithRelevanceDto build() {
            return new ArticleWithRelevanceDto(article, relevance);
        }
    }
}
