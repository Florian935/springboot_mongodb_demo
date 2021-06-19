package fr.iut.blankproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author florian935, NathanRenaud1997
 * Classe POJO qui correpond au modèle de classe à retourner lorsqu'on souhaite obtenir le nombre d'articles.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleCountAggregate {
    private Integer countArticle;
}
