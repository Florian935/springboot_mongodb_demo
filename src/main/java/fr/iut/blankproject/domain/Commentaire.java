package fr.iut.blankproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author florian935, NathanRenaud1997
 * Classe entité qui correpond aux commentaires.
 */
@Document(collection = "Commentaire")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Commentaire {
    @Id
    private String id;
    private String contenu;
    private User utilisateur;
}
