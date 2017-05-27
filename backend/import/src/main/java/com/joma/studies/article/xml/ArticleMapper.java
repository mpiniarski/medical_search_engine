package com.joma.studies.article.xml;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.dto.exception.ArticleBuildingException;
import com.joma.studies.article.xml.exception.InvalidArticleXmlFormat;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleMapper {
    private static final String TITLE_XPATH = "./MedlineCitation/Article/ArticleTitle";
    private static final String ABSTRACT_TEXT_XPATH = "./MedlineCitation/Article/Abstract/AbstractText";
    private static final String AUTHOR_XPATH = "./MedlineCitation/Article/AuthorList/Author";
    private static final String FORE_NAME_RELATIVE_XPATH = "./ForeName";
    private static final String LAST_NAME_RELATIVE_XPATH = "./LastName";
    private static final String DATE_XPATH = "./MedlineCitation/Article/ArticleDate";
    private static final String YEAR_RELATIVE_XPATH = "./Year";
    private static final String MONTH_RELATIVE_XPATH = "./Month";
    private static final String DAY_RELATIVE_XPATH = "./Day";

    public ArticleDto toDto(Node mainNode) throws InvalidArticleXmlFormat, ArticleBuildingException {
        return new ArticleDto.Builder()
                .withTitle(getSingleNode(mainNode, TITLE_XPATH).getStringValue())
                .withAbstractText(getAbstractText(mainNode))
                .withAuthors(getAuthors(mainNode))
                .withDate(getDateOrNull(mainNode))
                .build();
    }

    private Node getSingleNode(Node currentNode, String xpathExpression) throws InvalidArticleXmlFormat {
        List list = currentNode.selectNodes(xpathExpression);
        if (list.size() != 1) {
            throw new InvalidArticleXmlFormat();
        }
        Object object = list.get(0);
        return toNode(object);
    }

    private Node toNode(Object object) {
        if (!(object instanceof Node)) {
            throw new InvalidArticleXmlFormat();
        }
        return Node.class.cast(object);
    }

    private String getAbstractText(Node node) {
        List<String> abstractTexts = new ArrayList<>();
        for (Object object : node.selectNodes(ABSTRACT_TEXT_XPATH)) {
            Node abstractTextNode = toNode(object);
            abstractTexts.add(abstractTextNode.getStringValue());
        }
        return abstractTexts.stream()
                .collect(Collectors.joining(" "));
    }

    private List<String> getAuthors(Node node) {
        List<String> authors = new ArrayList<>();
        for (Object object : node.selectNodes(AUTHOR_XPATH)) {
            Node authorNode = toNode(object);
            String foreName = getSingleNode(authorNode, FORE_NAME_RELATIVE_XPATH).getStringValue();
            String lastName = getSingleNode(authorNode, LAST_NAME_RELATIVE_XPATH).getStringValue();
            authors.add(foreName + " " + lastName);
        }
        return authors;
    }


    private String getDateOrNull(Node node) {
        try {
            Node dateNode = getSingleNode(node, DATE_XPATH);
            String year = getSingleNode(dateNode, YEAR_RELATIVE_XPATH).getStringValue();
            String month = getSingleNode(dateNode, MONTH_RELATIVE_XPATH).getStringValue();
            String day = getSingleNode(dateNode, DAY_RELATIVE_XPATH).getStringValue();
            return year + "." + month + "." + day;
        }
        catch (InvalidArticleXmlFormat ignored){
            return null;
        }
    }

}
