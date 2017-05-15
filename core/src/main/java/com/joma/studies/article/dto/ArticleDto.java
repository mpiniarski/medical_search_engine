package com.joma.studies.article.dto;


import com.joma.studies.article.dto.exception.ArticleBuildingException;

public class ArticleDto {
    private final String title;
    private final String abstractText;

    private ArticleDto(String title, String abstractText) {
        this.title = title;
        this.abstractText = abstractText;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractText() {
        return abstractText;
    }


    public static final class Builder {
        private String title;
        private String abstractText;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAbstractText(String abstractText) {
            this.abstractText = abstractText;
            return this;
        }

        public ArticleDto build() throws ArticleBuildingException {
            if (title == null || abstractText == null){
               throw new ArticleBuildingException();
            }
            return new ArticleDto(title, abstractText);
        }
    }
}
