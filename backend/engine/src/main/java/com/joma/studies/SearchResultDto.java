package com.joma.studies;

import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;

import java.util.List;

public class SearchResultDto {
    private final QueryAnalysisDto query;
    private final List<RatedAndMeasuredArticleDto> articles;

    private SearchResultDto(QueryAnalysisDto query, List<RatedAndMeasuredArticleDto> articles) {
        this.query = query;
        this.articles = articles;
    }

    public QueryAnalysisDto getQuery() {
        return query;
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
