package com.joma.studies.article.lucile;

import com.joma.studies.article.dto.ArticleDto;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;

import java.util.stream.Collectors;

public class ArticleMapper {

    private static final String TITLE = "title";
    private static final String ABSTRACT_TEXT = "abstractText";
    private static final String AUTHORS = "authors";
    private static final String DATE = "date";

    private static final String JOIN_SEQUENCE = "&&";

    public Document toDocument(ArticleDto articleDto) {
        Document document = new Document();
        document.add(new TextField(TITLE, articleDto.getTitle(), Field.Store.YES));
        document.add(new TextField(ABSTRACT_TEXT, articleDto.getAbstractText(), Field.Store.YES));
        document.add(new StoredField(AUTHORS, articleDto.getAuthors()
                .stream()
                .collect(Collectors.joining(JOIN_SEQUENCE)))
        );
        if(articleDto.getDate() != null){
            document.add(new StoredField(DATE, articleDto.getDate()));
        }
        return document;
    }
}
