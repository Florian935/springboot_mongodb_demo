package fr.iut.blankproject.service;

import fr.iut.blankproject.domain.User;

import java.util.List;

public interface UserService extends CrudService<String, User> {
    List<User> findByPseudo(String pseudo);

    List<User> findByPseudoContaining(String pseudo);
}
