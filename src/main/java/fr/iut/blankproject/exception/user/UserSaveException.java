package fr.iut.blankproject.exception.user;

/**
 * @author florian935, NathanRenaud1997
 * Classe représentant une exception quand un utilisateur n'a pas pu être enregistré.
 */
public class UserSaveException extends RuntimeException {
    public UserSaveException(String message) {
        super(message);
    }
}
