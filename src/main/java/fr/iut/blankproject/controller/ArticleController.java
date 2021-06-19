package fr.iut.blankproject.controller;

import fr.iut.blankproject.domain.Article;
import fr.iut.blankproject.domain.ArticleAggregate;
import fr.iut.blankproject.domain.ArticleCountAggregate;
import fr.iut.blankproject.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author florian935, NathanRenaud1997
 * Controller qui permet de gérer les méthodes CRUD sur les articles
 */
@RestController
@RequestMapping("/api/v1.0/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    /**
     * Retourne la liste de tous les articles
     *
     * @return une liste d'articles
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Article>> findAll() {
        final List<Article> articles = articleService.findAll();

        return ResponseEntity.ok(articles);
    }

    /**
     * Retourne l'article correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de l'article
     * @return L'article correspondant à l'ID fourni
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> findById(@PathVariable String id) {
        final Article articleFound = articleService.findById(id);

        return ResponseEntity.ok(articleFound);
    }

    /**
     * Enregistre en base l'article fourni dans le corps de la requête
     *
     * @param article l'article à enregistrer
     * @return l'article enregistré
     */
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> save(@RequestBody Article article) {
        final Article articleSaved = articleService.save(article);

        return ResponseEntity.status(CREATED).body(articleSaved);
    }

    /**
     * Supprime l'article correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de l'article à supprimer
     * @return void
     */
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        articleService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Met à jour l'article correspondant à l'ID fourni en paramètre. Les données mises à jour sont celles fournies
     * dans le corps de la requête.
     *
     * @param id      l'id de l'article
     * @param article l'article à mettre à jour
     * @return l'article mis à jour
     */
    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> update(@PathVariable String id, @RequestBody Article article) {
        final Article articleUpdated = articleService.update(id, article);

        return ResponseEntity.ok(articleUpdated);
    }

    /**
     * Permet de chercher les articles qui correspondent au titre fourni en query param de la requête. A noter que le
     * titre doit correspondre exactement (sensible à la classe, prend en compte les espaces etc).
     *
     * @param titre titre à rechercher
     * @return la liste des articles correspondants
     */
    @GetMapping(path = "/search/titre/exact-matching", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByTitre(@RequestParam String titre) {
        final List<Article> articlesFound = articleService.findByTitre(titre);

        return articlesFound.size() == 0
                ? ResponseEntity.status(NOT_FOUND)
                .body("Aucun article correspondant au titre recherché n'a été trouvé.")
                : ResponseEntity.ok(articlesFound);
    }

    /**
     * Permet de chercher les articles dont le titre contient le critère de recherche passé en query param de la
     * requête. La recherche n'est pas sensible à la casse
     *
     * @param titre titre à rechercher
     * @return la liste des articles correspondants
     */
    @GetMapping(path = "/search/titre/containing", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByTitreContaining(@RequestParam String titre) {
        final List<Article> articlesFound = articleService.findByTitreContaining(titre);

        return articlesFound.size() == 0
                ? ResponseEntity.status(NOT_FOUND)
                .body("Aucun article correspondant au titre recherché n'a été trouvé.")
                : ResponseEntity.ok(articlesFound);
    }

    /**
     * Permet de chercher les articles dont la date de publication est comprise entre le paramètre "from" et le paramètre
     * "to" (qui sont fournis en paramètre de la requête)
     *
     * @param from date (borne inférieure incluse)
     * @param to   date (borne supérieure incluse)
     * @return la liste des articles correspondants
     */
    @GetMapping(path = "search/published-date/between", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByPublishedDateBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        final List<Article> articlesFound = articleService.findByPublishedDateBetween(from, to);

        return articlesFound.size() == 0
                ? ResponseEntity.status(NOT_FOUND)
                .body("Aucun article correspondant à la plage de date fournie n'a été trouvé.")
                : ResponseEntity.ok(articlesFound);
    }

    /**
     * Permet de chercher les articles dont le titre est contenu dans l'article (insensible à la casse), et dont la date de publication est
     * comprise entre le paramètre "from" et le paramètre "to" (qui sont fournis en paramètre de la requête)
     *
     * @param titre titre à rechercher
     * @param from  date (borne inférieure incluse)
     * @param to    date (borne supérieure incluse)
     * @return la liste des articles correspondants
     */
    @GetMapping(path = "/search", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByMultipleCriteria(@RequestParam String titre,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        final List<Article> articlesFound = articleService.findByTitreContainingAndPublishedDateBetween(titre, from, to);

        return articlesFound.size() == 0
                ? ResponseEntity.status(NOT_FOUND)
                .body("Aucun article correspondant aux critères de recherches fournis n'a été trouvé.")
                : ResponseEntity.ok(articlesFound);
    }

    /**
     * Permet d'obtenir le nombre d'articles présents en base de données.
     * @return le nombre de livres
     */
    @GetMapping(path = "/count", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleCountAggregate> countBy() {
        final ArticleCountAggregate count = articleService.countArticle();

        return ResponseEntity.ok(count);
    }

    /**
     * Permet d'obtenir le nombre de commentaire par article
     * @return nombre de commentaire par article
     */
    @GetMapping(path = "/commentary/count", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> countByCommentaryByArticle() {
        List<ArticleAggregate> articles = articleService.countCommentaryByArticle();

        return articles.size() == 0
                ? ResponseEntity.status(NOT_FOUND)
                .body("Aucun article n'est présent en base de données.")
                : ResponseEntity.ok(articles);
    }
}
