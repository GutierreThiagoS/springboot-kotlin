package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.data.vo.v1.AccountCredentialVO
import br.com.gutierre.productsdb.model.request.RequestSignUp
import br.com.gutierre.productsdb.model.request.RequestToken
import br.com.gutierre.productsdb.services.AuthService
import br.com.gutierre.productsdb.utils.decryptData
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @PostMapping(value = ["/signincode"])
    fun signInCode(@RequestBody request: RequestToken?): ResponseEntity<*> {
        println("crypto $request")
        val decode = decryptData(request!!.crypto)
        val data = ObjectMapper().readValue(decode, AccountCredentialVO::class.java)

        return if (data?.userName.isNullOrBlank() || data?.password.isNullOrBlank()) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.signIn(data!!)
    }

    @PostMapping(value = ["/signin"])
    fun signIn(@RequestBody data: AccountCredentialVO?): ResponseEntity<*> {
        println("data $data")
        return if (data?.userName.isNullOrBlank() || data?.password.isNullOrBlank()) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.signIn(data!!)
    }

    @PostMapping(value = ["/signuptoken"])
    fun signup(@RequestBody request: RequestToken?): ResponseEntity<*> {

        println("crypto $request")
        val decode = decryptData(request!!.crypto)
        println("decode $decode")
        val data = ObjectMapper().readValue(decode, RequestSignUp::class.java)
        println("data $data")

        return if (
            data?.userName.isNullOrBlank()
            || data?.password.isNullOrBlank()
            || data?.fullName.isNullOrBlank()
            ) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.signup(data)
    }

    @PostMapping(value = ["/signup"])
    fun signup(@RequestBody data: RequestSignUp?): ResponseEntity<*> {
        println("data $data")
        return if (
            data?.userName.isNullOrBlank()
            || data?.password.isNullOrBlank()
            || data?.fullName.isNullOrBlank()
            ) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.signup(data!!)
    }

    @PostMapping(value = ["/refresh/{username}"])
    fun refreshToken(
        @PathVariable("username") username: String?,
        @RequestHeader("Authorization") refreshToken: String?
    ): ResponseEntity<*> {
        return if (refreshToken.isNullOrBlank() || username.isNullOrBlank()) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.refreshToken(username, refreshToken)
    }

}