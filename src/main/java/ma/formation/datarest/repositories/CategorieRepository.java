package ma.formation.datarest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ma.formation.datarest.entities.Categorie;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
