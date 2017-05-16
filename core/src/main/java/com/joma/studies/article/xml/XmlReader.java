package com.joma.studies.article.xml;

import com.joma.studies.article.xml.exception.InvalidArticleXmlFormat;
import com.joma.studies.article.xml.exception.InvalidXmlException;
import com.joma.studies.common.Observable;
import com.joma.studies.common.Observer;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.dto.exception.ArticleBuildingException;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlReader implements Observable<ArticleDto> {
    private static final Logger logger = Logger.getLogger(XmlReader.class);


    private static final String ENTITY_ELEMENT_PATH = "/PubmedArticleSet/PubmedArticle";
    private final List<Observer<ArticleDto>> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer<ArticleDto> observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer<ArticleDto> observer) {
        observers.remove(observer);
    }

    private void notifyObservers(ArticleDto articleDto) {
        observers.forEach(a -> a.accept(this, articleDto));
    }

    public void read(InputStream inputStream) throws InvalidXmlException {
        try {
            SAXReader reader = new SAXReader();
            reader.addHandler(ENTITY_ELEMENT_PATH, new ElementHandler() {
                @Override
                public void onStart(ElementPath elementPath) {
                }

                @Override
                public void onEnd(ElementPath elementPath) {
                    try{
                        Document currentDocument = elementPath.getCurrent()
                                .getDocument();
                        ArticleDto.Builder builder = new ArticleDto.Builder();
                        builder.withTitle(getSingleNode(currentDocument, "//MedlineCitation/Article/ArticleTitle")
                                .getStringValue()
                        );
                        builder.withAbstractText(getSingleNode(currentDocument, "//MedlineCitation/Article/Abstract")
                                .getStringValue()
                        );

                        notifyObservers(builder.build());
                        elementPath.getCurrent().detach();
                    } catch (InvalidArticleXmlFormat | ArticleBuildingException e) {
                        logger.warn(e.getMessage());
                    }
                }
            });
            reader.read(inputStream);
        } catch (DocumentException e) {
            throw new InvalidXmlException();
        }


    }

    private Node getSingleNode(Document currentDocument, String xpathExpression) throws InvalidArticleXmlFormat {
        List list = currentDocument.selectNodes(xpathExpression);
        if (list.size() != 1){
            throw new InvalidArticleXmlFormat();
        }
        Object object = list.get(0);
        if (!(object instanceof Node)){
            throw new InvalidArticleXmlFormat();
        }
        return (Node) object;
    }

}
