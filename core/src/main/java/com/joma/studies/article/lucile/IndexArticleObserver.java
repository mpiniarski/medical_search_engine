package com.joma.studies.article.lucile;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.common.Observable;
import com.joma.studies.common.Observer;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

import javax.inject.Inject;
import java.io.IOException;


public class IndexArticleObserver implements Observer<ArticleDto> {

    private static final Logger logger = Logger.getLogger(IndexArticleObserver.class);

    private final IndexWriter indexWriter;
    private final ArticleMapper articleMapper;

    @Inject
    public IndexArticleObserver(Directory directory, Analyzer analyzer, ArticleMapper articleMapper) throws IOException {
        this.articleMapper = articleMapper;
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        indexWriter = new IndexWriter(directory, config);
    }

    @Override
    public void accept(Observable<ArticleDto> observable, ArticleDto item) {
        try {
            indexWriter.addDocument(
                    articleMapper.toDocument(item)
            );
            logger.info("Added document to index");
        } catch (IOException exception) {
            logger.warn("Unable to index document. Cause: " + exception.getMessage());
        }
    }

    public void finish() throws IOException {
        indexWriter.close();
    }
}
