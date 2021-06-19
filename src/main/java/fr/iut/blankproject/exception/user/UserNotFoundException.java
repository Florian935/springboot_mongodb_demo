package fr.iut.blankproject.exception.user;

/**
 * @author florian935, NathanRenaud1997
 * Classe représentant une exception quand un user n'a pas été trouvé.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
