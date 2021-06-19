package fr.iut.blankproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author florian935, NathanRenaud1997
 * Classe POJO pour les catégories.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Categorie {
    private String label;
}
