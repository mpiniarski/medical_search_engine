package com.joma.studies;

import com.joma.studies.query.dto.QueryAnalysisDto;

import java.util.List;

public class SearchResultDto {
    private final QueryAnalysisDto query;
    private final List<ArticleWithRelevanceDto> articles;

    private SearchResultDto(QueryAnalysisDto query, List<ArticleWithRelevanceDto> articles) {
        this.query = query;
        this.articles = articles;
    }

    public QueryAnalysisDto getQuery() {
        return query;
    }

    public List<ArticleWithRelevanceDto> getArticles() {
        return articles;
    }


    public static final class Builder {
        private QueryAnalysisDto queryAnalysisDto;
        private List<ArticleWithRelevanceDto> articles;

        public Builder withQueryAnalysisDto(QueryAnalysisDto queryAnalysisDto) {
            this.queryAnalysisDto = queryAnalysisDto;
            return this;
        }

        public Builder withArticles(List<ArticleWithRelevanceDto> articles) {
            this.articles = articles;
            return this;
        }

        public SearchResultDto build() {
            return new SearchResultDto(queryAnalysisDto, articles);
        }
    }
}
