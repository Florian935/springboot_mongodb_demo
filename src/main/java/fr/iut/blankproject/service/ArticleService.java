package fr.iut.blankproject.service;

import fr.iut.blankproject.domain.Article;
import fr.iut.blankproject.domain.ArticleAggregate;
import fr.iut.blankproject.domain.ArticleCountAggregate;

import java.util.Date;
import java.util.List;

public interface ArticleService extends CrudService<String, Article> {
    List<Article> findByTitre(String titre);

    List<Article> findByTitreContaining(String titre);

    List<Article> findByPublishedDateBetween(Date from, Date to);

    List<Article> findByTitreContainingAndPublishedDateBetween(String titre, Date from, Date to);

    ArticleCountAggregate countArticle();

    List<ArticleAggregate> countCommentaryByArticle();
}
