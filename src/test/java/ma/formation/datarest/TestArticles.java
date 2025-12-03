package ma.formation.datarest;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import ma.formation.datarest.entities.Article;
import ma.formation.datarest.repositories.ArticleRepository;

@SpringBootTest
@AutoConfigureMockMvc
class TestArticles {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void shouldListArticles() throws Exception {
        mockMvc.perform(get("/ecommerce"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.articles.length()").value(5))
                .andExpect(jsonPath("_embedded.articles[*].desc", hasItem("Laptop Pro 15")));
    }

    @Test
    void shouldGetArticleById() throws Exception {
        List<Article> articles = articleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        Article article = articles.get(0);

        mockMvc.perform(get("/ecommerce/{id}", article.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description").value(article.getDescription()))
                .andExpect(jsonPath("_links.self.href").exists());
    }
}
