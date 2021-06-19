package fr.iut.blankproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author florian935, NathanRenaud1997
 * Classe entité qui correpond aux utilisateurs.
 */
@Document(collection = "Utilisateur")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    private String id;
    @Indexed
    private String pseudo;
    private String password;
    @DBRef
    private List<Commentaire> commentaires;
    @DBRef
    private List<Article> articles;
    private Adresse adresse;
}
