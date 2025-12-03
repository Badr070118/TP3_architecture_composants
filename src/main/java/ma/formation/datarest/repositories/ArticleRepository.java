package ma.formation.datarest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.formation.datarest.entities.Article;
import ma.formation.datarest.projections.ArticleDTO;

@RepositoryRestResource(collectionResourceRel = "articles", path = "ecommerce", excerptProjection = ArticleDTO.class)
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByCategorie_Categorie(String categorie);
}
