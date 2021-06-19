package fr.iut.blankproject.service.implementation;

import fr.iut.blankproject.domain.Commentaire;
import fr.iut.blankproject.exception.commentaire.CommentaireNotFoundException;
import fr.iut.blankproject.exception.commentaire.CommentaireSaveException;
import fr.iut.blankproject.repository.CommentaireRepository;
import fr.iut.blankproject.service.CommentaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author florian935, NathanRenaud1997
 * Service qui permet de gérer la couche métier des commentaires
 */
@Service
@RequiredArgsConstructor
public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;

    /**
     * Retourne la liste de tous les commentaires
     *
     * @return les commentaires
     */
    @Override
    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    /**
     * Retourne le commentaire correspondant à l'ID fourni en parmamètre
     *
     * @param id id de le commentaire
     * @return le commentaire
     */
    @Override
    public Commentaire findById(String id) {
        return commentaireRepository
                .findById(id)
                .orElseThrow(
                        () -> new CommentaireNotFoundException(
                                String.format("Le commentaire avec l'id %s n'a pas été trouvé.", id)));
    }

    /**
     * Enregistre en base le commentaire fourni en paramètre
     *
     * @param commentaire le commentaire à enregistrer
     * @return le commentaire sauvegardé
     */
    @Override
    public Commentaire save(Commentaire commentaire) {
        checkCommentaireToSave(commentaire);
        return commentaireRepository.save(commentaire);
    }

    /**
     * Permet de vérifier que tous les attributs requis du commentaire qui va être enregistré sont présents
     *
     * @param commentaire le commentaire à vérifier
     */
    private void checkCommentaireToSave(Commentaire commentaire) {
        StringBuilder errorMessage = new StringBuilder();
        boolean thereIsAProblem = false;
        if (!StringUtils.hasText(commentaire.getContenu())) {
            errorMessage.append("Impossible d'enregistrer ce commentaire car le contenu est vide.");
            thereIsAProblem = true;
        }
        if (Objects.isNull(commentaire.getUtilisateur())) {
            errorMessage.append("Impossible d'enregistrer ce commentaire car l''utilisateur est vide.");
            thereIsAProblem = true;
        }

        if (thereIsAProblem) {
            throw new CommentaireSaveException(errorMessage.toString());
        }
    }

    /**
     * Supprime en base le commentaire correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de le commentaire
     */
    @Override
    public void deleteById(String id) {
        commentaireRepository.findById(id).ifPresentOrElse(
                user -> commentaireRepository.deleteById(id),
                () -> {
                    throw new CommentaireNotFoundException(
                            String.format(
                                    "Impossible de supprimer le commentaire avec l'id %s car il n'existe pas.",
                                    id));
                });
    }

    /**
     * Permet de mettre à jour en base le commentaire fourni en paramètre
     *
     * @param id          l'id du commentaire
     * @param commentaire le commentaire à mettre à jour
     * @return le commentaire mis à jour
     */
    @Override
    public Commentaire update(String id, Commentaire commentaire) {
        commentaire.setId(id);

        commentaireRepository.findById(id).ifPresentOrElse(
                u -> {
                    checkCommentaireToSave(commentaire);
                    commentaireRepository.save(commentaire);
                }, () -> {
                    throw new CommentaireSaveException(
                            String.format(
                                    "Impossible de mettre à jour le commentaire avec l'id %s car il n'existe pas.",
                                    id));
                }
        );

        return commentaireRepository.findById(id).get();
    }
}
