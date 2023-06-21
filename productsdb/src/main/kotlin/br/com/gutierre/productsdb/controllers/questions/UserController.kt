package br.com.gutierre.productsdb.controllers.questions

import br.com.gutierre.productsdb.model.User
import br.com.gutierre.productsdb.model.request.RequestLogin
import br.com.gutierre.productsdb.model.request.RequestRegister
import br.com.gutierre.productsdb.model.response.ResponseInfo
import br.com.gutierre.productsdb.model.response.ResponseLogin
import br.com.gutierre.productsdb.services.questions.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var service: UserService

    @GetMapping
    fun getAll(): List<User> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): User {
        return service.findById(id)
    }

    @PostMapping
    fun create(@RequestBody request: RequestRegister): ResponseInfo {
        return service.insert(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody requestLogin: RequestLogin): ResponseLogin {
        return service.login(requestLogin)
    }
}