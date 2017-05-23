package com.joma.studies;

import com.google.inject.Inject;
import com.joma.studies.article.lucile.IndexArticleObserver;
import com.joma.studies.article.xml.XmlReader;
import com.joma.studies.article.xml.exception.InvalidXmlException;
import com.joma.studies.config.ConfigProvider;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    private final XmlReader xmlReader;
    private final IndexArticleObserver indexArticleObserver;
    private final ConfigProvider configProvider;

    @Inject
    public App(XmlReader xmlReader, IndexArticleObserver indexArticleObserver, ConfigProvider configProvider) {
        this.xmlReader = xmlReader;
        this.indexArticleObserver = indexArticleObserver;
        this.configProvider = configProvider;
    }

    public void run() throws IOException, InvalidXmlException {
        xmlReader.addObserver(indexArticleObserver);
        xmlReader.read(new FileInputStream(
                new File(configProvider.getInputXmlPath())
        ));

        indexArticleObserver.finish();

        logger.info("Import ended with success");
    }

}
