package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.User
import br.com.gutierre.productsdb.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/v1")
class UserController {

    @Autowired
    lateinit var service: UserService

    @GetMapping
    fun getAll(): List<User> {
        return service.getAll()
    }

    @PatchMapping(value = ["/disable/{id}"])
    fun disableUserById(
        @PathVariable("id") id: Long
    ): User {
        return id.let { service.disableUser(it) }// ?: throw Exception("Id esta Nulo")
    }
}