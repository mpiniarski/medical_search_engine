package com.joma.studies.article.xml;

import com.google.inject.Inject;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.dto.exception.ArticleBuildingException;
import com.joma.studies.article.xml.exception.InvalidArticleXmlFormat;
import com.joma.studies.article.xml.exception.InvalidXmlException;
import com.joma.studies.common.Observable;
import com.joma.studies.common.Observer;
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
    private final ArticleMapper articleMapper;

    public void read(InputStream inputStream) throws InvalidXmlException {
        try {
            SAXReader reader = new SAXReader();
            reader.addHandler(ENTITY_ELEMENT_PATH, new ElementHandler() {
                @Override
                public void onStart(ElementPath elementPath) {
                }

                @Override
                public void onEnd(ElementPath elementPath) {
                    try {
                        Node node = elementPath.getCurrent().selectSingleNode(".");
                        notifyObservers(articleMapper.toDto(node));
                        elementPath.getCurrent().detach();
                    } catch (InvalidArticleXmlFormat | ArticleBuildingException e) {
                        logger.warn(e.getMessage());
                    }
                }
            });
            reader.read(inputStream);
        } catch (DocumentException exception) {
            throw new InvalidXmlException(exception);
        }


    }

    @Inject
    public XmlReader(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

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


}
