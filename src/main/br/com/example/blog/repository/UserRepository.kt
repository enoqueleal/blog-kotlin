package com.example.blog.repository

import com.example.blog.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findByLogin(login: String): User?

}