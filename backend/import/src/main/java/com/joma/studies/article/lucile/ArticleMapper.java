package com.joma.studies.article.lucile;

import com.joma.studies.article.dto.ArticleDto;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class ArticleMapper {

    private static final String TITLE = "title";
    private static final String ABSTRACT_TEXT = "abstractText";

    public Document toDocument(ArticleDto articleDto){
        Document document = new Document();
        document.add(new TextField(TITLE, articleDto.getTitle(), Field.Store.YES));
        document.add(new TextField(ABSTRACT_TEXT, articleDto.getAbstractText(), Field.Store.YES));
        return document;
    }
}
