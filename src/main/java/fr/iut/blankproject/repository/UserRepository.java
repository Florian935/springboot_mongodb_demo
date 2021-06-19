package fr.iut.blankproject.repository;

import fr.iut.blankproject.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByPseudo(String pseudo);

    @Query("{ pseudo: { $regex: ?0, $options: i } }")
    List<User> findByPseudoContaining(String pseudo);
}
