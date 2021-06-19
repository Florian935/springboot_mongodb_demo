package fr.iut.blankproject.service.implementation;

import fr.iut.blankproject.domain.Article;
import fr.iut.blankproject.domain.ArticleAggregate;
import fr.iut.blankproject.domain.ArticleCountAggregate;
import fr.iut.blankproject.exception.article.ArticleNotFoundException;
import fr.iut.blankproject.exception.article.ArticleSaveException;
import fr.iut.blankproject.repository.ArticleRepository;
import fr.iut.blankproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author florian935, NathanRenaud1997
 * Service qui permet de gérer la couche métier des articles
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    /**
     * Retourne la liste de tous les articles
     *
     * @return les articles
     */
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    /**
     * Retourne l'article correspondant à l'ID fourni en parmamètre
     *
     * @param id id de l'article
     * @return l'article
     */
    @Override
    public Article findById(String id) {
        return articleRepository
                .findById(id)
                .orElseThrow(
                        () -> new ArticleNotFoundException(
                                String.format("L'article avec l'id %s n'a pas été trouvé.", id)));
    }

    /**
     * Enregistre en base l'article fourni en paramètre
     *
     * @param article l'article à enregistrer
     * @return l'article sauvegardé
     */
    @Override
    public Article save(Article article) {
        checkArticleToSave(article);
        return articleRepository.save(article);
    }

    /**
     * Permet de vérifier que tous les attributs requis de l'article qui va être enregistré sont présents
     *
     * @param article l'article à vérifier
     */
    private void checkArticleToSave(Article article) {
        StringBuilder errorMessage = new StringBuilder();
        boolean thereIsAProblem = false;
        if (!StringUtils.hasText(article.getTitre())) {
            errorMessage.append("Impossible d'enregistrer cette article car le titre est manquant. \n");
            thereIsAProblem = true;
        }
        if (Objects.isNull(article.getUtilisateur())) {
            errorMessage.append("Impossible d'enregistrer cette article car aucun utilisateur n'y est rattaché.");
            thereIsAProblem = true;
        }

        if (thereIsAProblem) {
            throw new ArticleSaveException(errorMessage.toString());
        }
    }

    /**
     * Supprime en base l'article correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de l'article
     */
    @Override
    public void deleteById(String id) {
        articleRepository.findById(id).ifPresentOrElse(
                article -> articleRepository.deleteById(id),
                () -> {
                    throw new ArticleNotFoundException(
                            String.format("Impossible de supprimer l'article avec l'id %s car il n'existe pas.", id));
                });
    }

    /**
     * Permet de mettre à jour en base l'article fourni en paramètre
     *
     * @param id      l'id de l'article
     * @param article l'article à mettre à jour
     * @return l'article mis à jour
     */
    @Override
    public Article update(String id, Article article) {
        article.setId(id);

        articleRepository.findById(id).ifPresentOrElse(
                a -> {
                    checkArticleToSave(article);
                    articleRepository.save(article);
                }, () -> {
                    throw new ArticleSaveException(String.format("Impossible de mettre à jour l'article avec l'id %s car il n'existe pas.", id));
                }
        );

        return articleRepository.findById(id).get();
    }

    /**
     * Permet de chercher en base tous les articles dont le titre correspond exactement au titre fourni en paramètre.
     *
     * @param titre le titre à rechercher
     * @return la liste des articles correspondant
     */
    @Override
    public List<Article> findByTitre(String titre) {
        return articleRepository.findByTitre(titre);
    }

    /**
     * Permet de chercher en base tous les articles dont le titre contient le titre fourni en paramètre
     *
     * @param titre le titre à rechercher
     * @return la liste des articles correspondant
     */
    @Override
    public List<Article> findByTitreContaining(String titre) {
        return articleRepository.findByTitreContaining(titre);
    }

    /**
     * Permet de chercher en base tous les articles dont la date de publication est comprise dans la plage des dates
     * fournies. (bornes incluses)
     *
     * @param from à partir de quelle date
     * @param to   jusqu'à quelle date
     * @return la liste des articles
     */
    @Override
    public List<Article> findByPublishedDateBetween(Date from, Date to) {
        return articleRepository.findByPublishedDateBetween(from, to);
    }

    /**
     * Permet de chercher en base tous les articles dont le titre contient le titre fourni en paramètre et dont la date
     * de publication est comprise dans la plage des dates fournies. (bornes incluses)
     *
     * @param titre titre à rechercher
     * @param from  à partir de quelle date
     * @param to    jusqu'à quelle date
     * @return la liste des articles
     */
    @Override
    public List<Article> findByTitreContainingAndPublishedDateBetween(String titre, Date from, Date to) {
        return articleRepository.findByTitreContainingAndPublishedDateBetween(titre, from, to);
    }

    /**
     * Permet de compter le nombre d'articles en base
     *
     * @return le nombre d'articles
     */
    @Override
    public ArticleCountAggregate countArticle() {
        return articleRepository.countArticle();
    }

    /**
     * Permet de compter le nombre de commentaires par articles
     *
     * @return le nombre de commentaires par article
     */
    @Override
    public List<ArticleAggregate> countCommentaryByArticle() {
        return articleRepository.countCommentaryByArticle();
    }
}
