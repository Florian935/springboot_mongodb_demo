package fr.iut.blankproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author florian935, NathanRenaud1997
 * Classe POJO pour les adresses.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Adresse {
    private String rue;
    private String codePostal;
}
