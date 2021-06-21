package fr.iut.blankproject.exception;

import fr.iut.blankproject.exception.article.ArticleNotFoundException;
import fr.iut.blankproject.exception.article.ArticleSaveException;
import fr.iut.blankproject.exception.commentaire.CommentaireNotFoundException;
import fr.iut.blankproject.exception.commentaire.CommentaireSaveException;
import fr.iut.blankproject.exception.user.UserNotFoundException;
import fr.iut.blankproject.exception.user.UserSaveException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * Classe permettant d'intercepter les exceptions déclarées ci-dessous dans les différentes méthodes et de faire
 * correspondre un code HTTP adapté en fonction de l'exception levée.
 */
@ControllerAdvice
public class CustomExceptionAdvisor {

    /**
     * Retourne un code HTTP 404 lorsque les exceptions ci-dessous sont levées.
     * @param exception exception levée
     * @return le message de l'exception
     */
    @ResponseBody
    @ExceptionHandler({ArticleNotFoundException.class, UserNotFoundException.class, CommentaireNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    protected String notFoundExceptionHandler(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Retourne un code HTTP 422 lorsque les exceptions ci-dessous sont levées.
     * @param exception exception levée
     * @return le message de l'exception
     */
    @ResponseBody
    @ExceptionHandler({ArticleSaveException.class, UserSaveException.class, CommentaireSaveException.class})
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    protected String saveExceptionHandler(Exception exception) { return exception.getMessage(); }
}
