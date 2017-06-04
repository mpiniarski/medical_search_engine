package com.joma.studies.article.repository;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.repository.exception.RepositoryException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ArticleRepository {
    private final Directory directory;
    private final RepositoryQueryGenerator repositoryQueryGenerator;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleRepository(Directory directory, RepositoryQueryGenerator repositoryQueryGenerator, ArticleMapper articleMapper) {
        this.directory = directory;
        this.repositoryQueryGenerator = repositoryQueryGenerator;
        this.articleMapper = articleMapper;
    }

    public List<ArticleDto> findByWordSet(Set<String> words) throws RepositoryException {
        try {
            List<ArticleDto> result = new ArrayList<>();
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            ScoreDoc[] hits = indexSearcher.search(repositoryQueryGenerator.generate(words), Integer.MAX_VALUE)
                    .scoreDocs;
            for (ScoreDoc hit : hits) {
                result.add(articleMapper.toArticleDto(
                        indexSearcher.doc(hit.doc)
                ));
            }
            indexReader.close();
            return result;
        } catch (IOException exception) {
            throw new RepositoryException(exception);
        }
    }
}
