package fr.iut.blankproject.controller;

import fr.iut.blankproject.domain.User;
import fr.iut.blankproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author florian935, NathanRenaud1997
 * Controller qui permet de gérer les méthodes CRUD sur les utilisateurs
 */
@RestController
@RequestMapping("/api/v1.0/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Retourne la liste de tous les utilisateurs
     *
     * @return une liste d'utilisateurs
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    /**
     * Retourne l'utilisateur correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de l'utilisateur
     * @return L'utilisateur correspondant à l'ID fourni
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findById(@PathVariable String id) {
        final User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    /**
     * Enregistre en base l'utilisateur fourni dans le corps de la requête
     *
     * @param user l'utilisateur à enregistrer
     * @return l'utilisateur enregistré
     */
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> save(@RequestBody User user) {
        final User userSaved = userService.save(user);

        return ResponseEntity.status(CREATED).body(userSaved);
    }

    /**
     * Supprime l'utilisateur correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de l'utilisateur à supprimer
     * @return void
     */
    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Met à jour l'utilisateur correspondant à l'ID fourni en paramètre. Les données mises à jour sont celles fournies
     * dans le corps de la requête.
     *
     * @param id      l'id de l'utilisateur
     * @param user l'utilisateur à mettre à jour
     * @return l'utilisateur mis à jour
     */
    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        final User userUpdated = userService.update(id, user);

        return ResponseEntity.ok(userUpdated);
    }

    /**
     * Permet de chercher les utilisateurs qui correspondent au pseudo fourni en query param de la requête. A noter que le
     * pseudo doit correspondre exactement (sensible à la classe, prend en compte les espaces etc).
     *
     * @param pseudo pseudo à rechercher
     * @return la liste des utilisateurs correspondants
     */
    @GetMapping(path = "/search/pseudo/exact-matching", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByPseudo(@RequestParam String pseudo) {
        List<User> usersFound = userService.findByPseudo(pseudo);

        return usersFound.size() == 0
                ? ResponseEntity.status(NOT_FOUND)
                .body("Aucun utilisateur correspondant au pseudo recherché n'a été trouvé.")
                : ResponseEntity.ok(usersFound);
    }

    /**
     * Permet de chercher les utilisateurs dont le titre contient le critère de recherche passé en query param de la
     * requête. La recherche n'est pas sensible à la casse
     *
     * @param pseudo pseudo à rechercher
     * @return la liste des utilisateurs correspondants
     */
    @GetMapping(path = "/search/pseudo/containing", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByPseudoContaining(@RequestParam String pseudo) {
        final List<User> usersFound = userService.findByPseudoContaining(pseudo);

        return usersFound.size() == 0
                ? ResponseEntity.status(NOT_FOUND)
                .body("Aucun utilisateur correspondant au pseudo recherché n'a été trouvé.")
                : ResponseEntity.ok(usersFound);
    }
}
