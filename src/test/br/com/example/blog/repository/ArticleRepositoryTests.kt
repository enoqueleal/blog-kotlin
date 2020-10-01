package com.example.blog.repository

import com.example.blog.model.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.*
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class ArticleRepositoryTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val juergen = User("springjuergen", "Juergen", "Hoeller")
        entityManager.persist(juergen)
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        Assertions.assertThat(found).isEqualTo(article)
    }

}
