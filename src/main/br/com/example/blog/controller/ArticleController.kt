package com.example.blog.controller

import com.example.blog.repository.ArticleRepository
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

	@GetMapping("/")
	fun findAll() = repository.findAllByOrderByAddedAtDesc()

	@GetMapping("/{slug}")
	fun findOne(@PathVariable slug: String) =
			repository.findBySlug(slug) ?: throw ResponseStatusException(NOT_FOUND, "This article does not exist")

}


