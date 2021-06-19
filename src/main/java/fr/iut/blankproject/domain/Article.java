package fr.iut.blankproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @author florian935, NathanRenaud1997
 * Classe entité qui correpond aux articles.
 */
@Document(collection = "Article")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Article {
    @Id
    private String id;
    @Indexed
    private String titre;
    private String contenu;
    @Indexed
    private User utilisateur;
    @CreatedDate
    @Indexed
    private Date publishedDate;
    private List<Categorie> categories;
    private List<Commentaire> commentaires;
}
