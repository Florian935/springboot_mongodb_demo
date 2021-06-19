package fr.iut.blankproject.exception.article;

/**
 * @author florian935, NathanRenaud1997
 * Classe représentant une exception quand un article n'a pas été trouvé.
 */
public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
