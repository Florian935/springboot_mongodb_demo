package fr.iut.blankproject.repository;

import fr.iut.blankproject.domain.Article;
import fr.iut.blankproject.domain.ArticleAggregate;
import fr.iut.blankproject.domain.ArticleCountAggregate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    List<Article> findByTitre(String titre);

    @Query("{ titre: { $regex: ?0, $options: i } }")
    List<Article> findByTitreContaining(String titre);

    List<Article> findByPublishedDateBetween(Date from, Date to);

    @Query("{ titre: { $regex: ?0, $options: i }, publishedDate : { $gte: ?1, $lte: ?2 } }")
    List<Article> findByTitreContainingAndPublishedDateBetween(String titre, Date from, Date to);

    @Aggregation("{ $group: { _id : null, countArticle: { $sum: 1 } } } ")
    ArticleCountAggregate countArticle();

    @Aggregation("{ $project:\n" +
            "    {\n" +
            "        \"titre\": 1,\n" +
            "        \"countCommentary\": { $cond: { if: { $ifNull: [ \"$commentaires\", 0 ] }, then: { $size: \"$commentaires\" }, else: 0 } }\n" +
            "    }\n" +
            "    }")
    List<ArticleAggregate> countCommentaryByArticle();
}
