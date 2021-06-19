package fr.iut.blankproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author florian935, NathanRenaud1997
 * Classe POJO qui correpond au modèle de classe à retourner lorsqu'on souhaite obtenir le nombre de commentaire
 * par article.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleAggregate {
    @Id
    private String id;
    private String titre;
    private Integer countCommentary;
}
