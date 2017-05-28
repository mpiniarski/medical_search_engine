package com.joma.studies;

import com.joma.studies.query.dto.QueryAnalysisDto;

import java.util.List;

public class SearchResultDto {
    private final QueryAnalysisDto query;
    private final List<ArticleWithMeasureMapAndRelevanceDto> articles;

    private SearchResultDto(QueryAnalysisDto query, List<ArticleWithMeasureMapAndRelevanceDto> articles) {
        this.query = query;
        this.articles = articles;
    }

    public QueryAnalysisDto getQuery() {
        return query;
    }

    public List<ArticleWithMeasureMapAndRelevanceDto> getArticles() {
        return articles;
    }


    public static final class Builder {
        private QueryAnalysisDto queryAnalysisDto;
        private List<ArticleWithMeasureMapAndRelevanceDto> articles;

        public Builder withQueryAnalysisDto(QueryAnalysisDto queryAnalysisDto) {
            this.queryAnalysisDto = queryAnalysisDto;
            return this;
        }

        public Builder withArticles(List<ArticleWithMeasureMapAndRelevanceDto> articles) {
            this.articles = articles;
            return this;
        }

        public SearchResultDto build() {
            return new SearchResultDto(queryAnalysisDto, articles);
        }
    }
}
