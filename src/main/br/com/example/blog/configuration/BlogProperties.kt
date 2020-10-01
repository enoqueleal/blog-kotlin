package com.example.blog.configuration

import org.springframework.boot.context.properties.*

@ConstructorBinding
@ConfigurationProperties("blog")
data class BlogProperties(var title: String, val banner: Banner) {
	data class Banner(val title: String? = null, val content: String)
}
