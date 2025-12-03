package ma.formation.datarest;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import ma.formation.datarest.entities.Article;
import ma.formation.datarest.entities.Categorie;
import ma.formation.datarest.repositories.ArticleRepository;
import ma.formation.datarest.repositories.CategorieRepository;

@SpringBootApplication
@Slf4j
public class DataRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataRestApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(ArticleRepository articleRepository, CategorieRepository categorieRepository) {
        return args -> {
            if (articleRepository.count() > 0 || categorieRepository.count() > 0) {
                log.info("Sample data already present - skipping initialization");
                return;
            }

            Categorie informatique = categorieRepository.save(Categorie.builder().categorie("Informatique").build());
            Categorie bureautique = categorieRepository.save(Categorie.builder().categorie("Bureautique").build());
            Categorie maison = categorieRepository.save(Categorie.builder().categorie("Maison").build());

            List<Article> articles = List.of(
                    Article.builder().description("Laptop Pro 15").price(1899.0).quantity(8).categorie(informatique)
                            .build(),
                    Article.builder().description("Imprimante Laser X").price(349.99).quantity(12).categorie(bureautique)
                            .build(),
                    Article.builder().description("Chaise Ergonomique").price(229.5).quantity(20).categorie(maison)
                            .build(),
                    Article.builder().description("Station d'accueil USB-C").price(149.0).quantity(15)
                            .categorie(informatique).build(),
                    Article.builder().description("Pack Stylos Premium").price(19.9).quantity(150).categorie(bureautique)
                            .build());

            articleRepository.saveAll(articles);
            log.info("Sample data initialized: {} categories / {} articles", categorieRepository.count(),
                    articleRepository.count());
        };
    }
}
