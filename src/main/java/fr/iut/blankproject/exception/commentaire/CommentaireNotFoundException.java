package fr.iut.blankproject.exception.commentaire;

/**
 * @author florian935, NathanRenaud1997
 * Classe représentant une exception quand un commentaire n'a pas été trouvé.
 */
public class CommentaireNotFoundException extends RuntimeException {
    public CommentaireNotFoundException(String message) {
        super(message);
    }
}
