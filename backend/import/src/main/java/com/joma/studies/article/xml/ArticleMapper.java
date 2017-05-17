package com.joma.studies.article.xml;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.dto.exception.ArticleBuildingException;
import com.joma.studies.article.xml.exception.InvalidArticleXmlFormat;
import org.dom4j.Document;
import org.dom4j.Node;

import java.util.List;

public class ArticleMapper {
    private static final String TITLE_XPATH = "//MedlineCitation/Article/ArticleTitle";
    private static final String ABSTRACT_XPATH = "//MedlineCitation/Article/Abstract";

    public ArticleDto toDto(Document document) throws InvalidArticleXmlFormat, ArticleBuildingException {
        return new ArticleDto.Builder()
                .withTitle(getSingleNode(document, TITLE_XPATH).getStringValue())
                .withAbstractText(getSingleNode(document, ABSTRACT_XPATH).getStringValue())
                .build();
    }

    private Node getSingleNode(Document currentDocument, String xpathExpression) throws InvalidArticleXmlFormat {
        List list = currentDocument.selectNodes(xpathExpression);
        if (list.size() != 1) {
            throw new InvalidArticleXmlFormat();
        }
        Object object = list.get(0);
        if (!(object instanceof Node)) {
            throw new InvalidArticleXmlFormat();
        }
        return (Node) object;
    }

}
