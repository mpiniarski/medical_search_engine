package com.joma.studies.article.repository;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.dto.exception.ArticleBuildingException;
import org.apache.lucene.document.Document;

public class ArticleMapper {

    private static final String TITLE = "title";
    private static final String ABSTRACT = "abstractText";

    ArticleDto toArticleDto(Document document) throws ArticleBuildingException {
        return new ArticleDto.Builder()
                .withTitle(document.get(TITLE))
                .withAbstractText(document.get(ABSTRACT))
                .build();
    }
}
