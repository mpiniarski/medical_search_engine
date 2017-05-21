package com.joma.studies.article.repository;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.repository.exception.RepositoryException;

import java.util.List;
import java.util.Set;

public class ArticleRepository {

    public List<ArticleDto> findByQuery(Set<String> words) throws RepositoryException{
        return null;
    }
}
