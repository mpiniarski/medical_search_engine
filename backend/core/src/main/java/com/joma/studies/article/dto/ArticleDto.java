package com.joma.studies.article.dto;


import java.util.List;

public class ArticleDto {
    private final String title;
    private final String abstractText;
    private final List<String> authors;
    private final String date;

    private ArticleDto(String title, String abstractText, List<String> authors, String date) {
        this.title = title;
        this.abstractText = abstractText;
        this.authors = authors;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractText() {
        return abstractText;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getDate() {
        return date;
    }

    //TODO right place for it?
    @Override
    public String toString() {
        return title + " " + abstractText;
    }


    public static final class Builder {
        private String title;
        private String abstractText;
        private List<String> authors;
        private String date;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withAbstractText(String abstractText) {
            this.abstractText = abstractText;
            return this;
        }

        public Builder withAuthors(List<String> authors) {
            this.authors = authors;
            return this;
        }

        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        public ArticleDto build() {
            return new ArticleDto(title, abstractText, authors, date);
        }
    }
}
