package com.joma.studies;

import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;

import java.util.List;

public class SearchResultDto {
    public static final int ARTICLES_IS_RESULT = 1000;
    private final QueryAnalysisDto query;
    private final int articleNumber;
    private final List<RatedAndMeasuredArticleDto> articles;

    private SearchResultDto(QueryAnalysisDto query, List<RatedAndMeasuredArticleDto> articles) {
        this.query = query;
        this.articles = articles.subList(0, ARTICLES_IS_RESULT-1);
        this.articleNumber = articles.size();
    }

    public QueryAnalysisDto getQuery() {
        return query;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public List<RatedAndMeasuredArticleDto> getArticles() {
        return articles;
    }

    public static final class Builder {
        private QueryAnalysisDto queryAnalysisDto;
        private List<RatedAndMeasuredArticleDto> articles;

        public Builder withQueryAnalysisDto(QueryAnalysisDto queryAnalysisDto) {
            this.queryAnalysisDto = queryAnalysisDto;
            return this;
        }

        public Builder withArticles(List<RatedAndMeasuredArticleDto> articles) {
            this.articles = articles;
            return this;
        }

        public SearchResultDto build() {
            return new SearchResultDto(queryAnalysisDto, articles);
        }
    }
}
