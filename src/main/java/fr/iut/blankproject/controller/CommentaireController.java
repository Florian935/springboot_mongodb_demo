package fr.iut.blankproject.controller;

import fr.iut.blankproject.domain.Commentaire;
import fr.iut.blankproject.service.CommentaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author florian935, NathanRenaud1997
 * Controller qui permet de gérer les méthodes CRUD sur les commentaires
 */
@RestController
@RequestMapping("/api/v1.0/commentaires")
@RequiredArgsConstructor
public class CommentaireController {
    private final CommentaireService commentaireService;

    /**
     * Retourne la liste de tous les commentaires
     *
     * @return une liste de commentaires
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Commentaire>> findAll() {
        final List<Commentaire> commentaires = commentaireService.findAll();

        return ResponseEntity.ok(commentaires);
    }

    /**
     * Retourne le commentaire correspondant à l'ID fourni en paramètre
     *
     * @param id l'id du commentaire
     * @return Le commentaire correspondant à l'ID fourni
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Commentaire> findById(@PathVariable String id) {
        final Commentaire commentaire = commentaireService.findById(id);

        return ResponseEntity.ok(commentaire);
    }

    /**
     * Enregistre en base le commentaire fourni dans le corps de la requête
     *
     * @param commentaire le commentaire à enregistrer
     * @return le commentaire enregistré
     */
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Commentaire> save(@RequestBody Commentaire commentaire) {
        final Commentaire commentaireSaved = commentaireService.save(commentaire);

        return ResponseEntity.status(CREATED).body(commentaireSaved);
    }

    /**
     * Supprime le commentaire correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de le commentaire à supprimer
     * @return void
     */
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        commentaireService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Met à jour le commentaire correspondant à l'ID fourni en paramètre. Les données mises à jour sont celles fournies
     * dans le corps de la requête.
     *
     * @param id      l'id de le commentaire
     * @param commentaire le commentaire mis à jour
     * @return le commentaire mis à jour
     */
    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Commentaire> update(@PathVariable String id, @RequestBody Commentaire commentaire) {
        final Commentaire commentaireUpdated = commentaireService.update(id, commentaire);

        return ResponseEntity.ok(commentaireUpdated);
    }
}
