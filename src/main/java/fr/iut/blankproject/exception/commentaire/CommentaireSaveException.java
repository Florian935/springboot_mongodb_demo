package fr.iut.blankproject.exception.commentaire;

/**
 * @author florian935, NathanRenaud1997
 * Classe représentant une exception quand un commentaire n'a pas pu être enregistré.
 */
public class CommentaireSaveException extends RuntimeException {
    public CommentaireSaveException(String message) {
        super(message);
    }
}
