package com.example.blog.controller

import com.example.blog.configuration.BlogProperties
import com.example.blog.model.*
import com.example.blog.repository.ArticleRepository
import com.example.blog.util.format
import org.springframework.http.HttpStatus.*
import org.springframework.stereotype.Controller
import org.springframework.ui.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(private val repository: ArticleRepository,
					 private val properties: BlogProperties) {

	@GetMapping("/")
	fun blog(model: Model): String {
		model["title"] = properties.title
		model["banner"] = properties.banner
		model["articles"] = repository.findAllByOrderByAddedAtDesc().map { it.render() }
		return "blog"
	}

	@GetMapping("/article/{slug}")
	fun article(@PathVariable slug: String, model: Model): String {
		val article = repository
				.findBySlug(slug)
				?.render()
				?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")
		model["title"] = article.title
		model["article"] = article
		return "article"
	}

	fun Article.render() = RenderedArticle(
            slug,
            title,
            headline,
            content,
            author,
            addedAt.format()
    )

	data class RenderedArticle(
			val slug: String,
			val title: String,
			val headline: String,
			val content: String,
			val author: User,
			val addedAt: String)

}
