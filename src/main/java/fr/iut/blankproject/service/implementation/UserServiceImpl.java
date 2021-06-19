package fr.iut.blankproject.service.implementation;

import fr.iut.blankproject.domain.User;
import fr.iut.blankproject.exception.user.UserNotFoundException;
import fr.iut.blankproject.exception.user.UserSaveException;
import fr.iut.blankproject.repository.UserRepository;
import fr.iut.blankproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author florian935, NathanRenaud1997
 * Service qui permet de gérer la couche métier des utilisateurs
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    /**
     * Retourne la liste de tous les utilisateurs
     *
     * @return les utilisateurs
     */
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Retourne l'utilisateur correspondant à l'ID fourni en parmamètre
     *
     * @param id id de l'utilisateur
     * @return l'utilisateur
     */
    @Override
    public User findById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException(
                                String.format("L'utilisateur avec l'id %s n'a pas été trouvé.", id)));
    }

    /**
     * Enregistre en base l'utilisateur fourni en paramètre
     *
     * @param user l'utilisateur à enregistrer
     * @return l'utilisateur sauvegardé
     */
    @Override
    public User save(User user) {
        checkUserToSave(user);
        return userRepository.save(user);
    }

    /**
     * Permet de vérifier que tous les attributs requis de l'utilisateur qui va être enregistré sont présents
     *
     * @param user l'utilisateur à vérifier
     */
    private void checkUserToSave(User user) {
        StringBuilder errorMessage = new StringBuilder();
        boolean thereIsAProblem = false;
        if (!StringUtils.hasText(user.getPseudo())) {
            errorMessage.append("Impossible d'enregistrer cet utilisateur car le pseudo est manquant. \n");
            thereIsAProblem = true;
        }
        if (!StringUtils.hasText(user.getPassword())) {
            errorMessage.append("Impossible d'enregistrer cet utilisateur car le mot de passe est manquant. \n");
            thereIsAProblem = true;
        }
        if (Objects.isNull(user.getAdresse())) {
            errorMessage.append("Impossible d'enregistrer cet utilisateur car l'adresse n'est pas renseignée.");
            thereIsAProblem = true;
        }

        if (thereIsAProblem) {
            throw new UserSaveException(errorMessage.toString());
        }
    }

    /**
     * Supprime en base l'utilisateur correspondant à l'ID fourni en paramètre
     *
     * @param id l'id de l'utilisateur
     */
    @Override
    public void deleteById(String id) {
        userRepository.findById(id).ifPresentOrElse(
                user -> userRepository.deleteById(id),
                () -> {
                    throw new UserNotFoundException(
                            String.format(
                                    "Impossible de supprimer l'utilisateur avec l'id %s car il n'existe pas.",
                                    id));
                });
    }

    /**
     * Permet de mettre à jour en base l'utilisateur fourni en paramètre
     *
     * @param id   l'id de l'utilisateur
     * @param user l'utilisateur à mettre à jour
     * @return l'utilisateur mis à jour
     */
    @Override
    public User update(String id, User user) {
        user.setId(id);

        userRepository.findById(id).ifPresentOrElse(
                u -> {
                    checkUserToSave(user);
                    userRepository.save(user);
                }, () -> {
                    throw new UserSaveException(
                            String.format(
                                    "Impossible de mettre à jour l'utilisateur avec l'id %s car il n'existe pas.",
                                    id));
                }
        );

        return userRepository.findById(id).get();
    }

    /**
     * Permet de chercher en base tous les utilisateurs dont le pseudo correspond exactement au pseudo fourni en paramètre.
     *
     * @param pseudo le pseudo à rechercher
     * @return la liste des utilisateurs correspondant
     */
    @Override
    public List<User> findByPseudo(String pseudo) {
        return userRepository.findByPseudo(pseudo);
    }

    /**
     * Permet de chercher en base tous les utilisateurs dont le pseudo contient le pseudo fourni en paramètre
     *
     * @param pseudo le pseudo à rechercher
     * @return la liste des utilisateurs correspondant
     */
    @Override
    public List<User> findByPseudoContaining(String pseudo) {
        return userRepository.findByPseudoContaining(pseudo);
    }
}
