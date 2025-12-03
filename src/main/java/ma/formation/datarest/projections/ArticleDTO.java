package ma.formation.datarest.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import ma.formation.datarest.entities.Article;

@Projection(name = "articleDto", types = Article.class)
public interface ArticleDTO {

    Long getId();

    @Value("#{target.description}")
    String getDesc();

    double getPrice();

    @Value("#{target.quantity}")
    int getQuant();

    @Value("#{target.categorie != null ? target.categorie.categorie : null}")
    String getCat();
}
