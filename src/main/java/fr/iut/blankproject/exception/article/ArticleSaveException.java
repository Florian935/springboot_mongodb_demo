package fr.iut.blankproject.exception.article;

/**
 * @author florian935, NathanRenaud1997
 * Classe représentant une exception quand un article n'a pas pu être enregistré.
 */
public class ArticleSaveException extends RuntimeException {
    public ArticleSaveException(String message) {
        super(message);
    }
}
