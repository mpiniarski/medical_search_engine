package com.joma.studies.article.lucile;

import com.joma.studies.article.dto.ArticleDto;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class ArticleMapper {
    public Document toDocument(ArticleDto articleDto){
        Document document = new Document();
        document.add(new TextField("title", articleDto.getTitle(), Field.Store.YES));
        document.add(new TextField("abstractText", articleDto.getAbstractText(), Field.Store.YES));
        return document;
    }
}
