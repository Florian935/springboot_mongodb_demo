package fr.iut.blankproject.repository;

import fr.iut.blankproject.domain.Commentaire;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends MongoRepository<Commentaire, String> {
}
