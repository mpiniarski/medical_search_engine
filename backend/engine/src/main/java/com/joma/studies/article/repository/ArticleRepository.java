package com.joma.studies.article.repository;

import com.google.inject.Inject;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.repository.exception.RepositoryException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ArticleRepository {
    private final Directory directory;
    private final QueryGenerator queryGenerator;
    private final ArticleMapper articleMapper;

    @Inject
    public ArticleRepository(Directory directory, QueryGenerator queryGenerator, ArticleMapper articleMapper) {
        this.directory = directory;
        this.queryGenerator = queryGenerator;
        this.articleMapper = articleMapper;
    }

    public List<ArticleDto> findByQuery(Set<String> words) throws RepositoryException {
        try {
            List<ArticleDto> result = new ArrayList<>();
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            ScoreDoc[] hits = indexSearcher.search(queryGenerator.generate(words), 1000)
                    .scoreDocs;
            for (ScoreDoc hit : hits) {
                result.add(articleMapper.toArticleDto(
                        indexSearcher.doc(hit.doc)
                ));
            }
            indexReader.close();
            directory.close();
            return result;
        } catch (IOException exception) {
            throw new RepositoryException(exception);
        }
    }
}
