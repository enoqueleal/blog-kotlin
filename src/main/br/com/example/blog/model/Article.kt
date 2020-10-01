package com.example.blog.model

import com.example.blog.util.toSlug
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Article(
        var title: String,
        var headline: String,
        var content: String,
        @ManyToOne var author: User,
        var slug: String = title.toSlug(),
        var addedAt: LocalDateTime = LocalDateTime.now(),
        @Id @GeneratedValue var id: Long? = null)