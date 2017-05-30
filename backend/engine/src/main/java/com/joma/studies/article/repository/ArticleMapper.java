package com.joma.studies.article.repository;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.dto.exception.ArticleBuildingException;
import org.apache.lucene.document.Document;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ArticleMapper {

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String ABSTRACT = "abstractText";
    private static final String AUTHORS = "authors";
    private static final String DATE = "date";

    private static final String JOIN_SEQUENCE = "&&";

    ArticleDto toArticleDto(Document document) throws ArticleBuildingException {
        return new ArticleDto.Builder()
                .withId(Long.valueOf(document.get(ID)))
                .withTitle(document.get(TITLE))
                .withAbstractText(document.get(ABSTRACT))
                .withAuthors(Arrays.asList(
                        document.get(AUTHORS)
                                .split(JOIN_SEQUENCE)
                ))
                .withDate(document.get(DATE))
                .build();
    }
}
