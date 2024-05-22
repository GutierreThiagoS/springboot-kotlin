package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.data.vo.v1.AccountCredentialVO
import br.com.gutierre.productsdb.model.request.RequestSignUp
import br.com.gutierre.productsdb.model.request.RequestToken
import br.com.gutierre.productsdb.services.AuthService
import br.com.gutierre.productsdb.utils.decryptData
import br.com.gutierre.util.MediaType
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
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
@Tag(name = "Auth", description = "Endpoints de Autorização de Usuario")
class AuthController {

    @Autowired
    lateinit var authService: AuthService

    @Operation(
        summary = "Login Code", description = "Login em codigo",
        tags = ["Auth"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ResponseEntity::class))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
        ]
    )
    @PostMapping(value = ["/signincode"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun signInCode(@RequestBody request: RequestToken?): ResponseEntity<*> {
        println("crypto $request")
        val decode = decryptData(request!!.crypto)
        val data = ObjectMapper().readValue(decode, AccountCredentialVO::class.java)

        return if (data?.userName.isNullOrBlank() || data?.password.isNullOrBlank()) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.signIn(data!!)
    }

    @Operation(
        summary = "Login", description = "Fazer Login e gerar Autorização",
        tags = ["Auth"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ResponseEntity::class))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
        ]
    )
    @PostMapping(value = ["/signin"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun signIn(@RequestBody data: AccountCredentialVO?): ResponseEntity<*> {
        println("data $data")
        return if (data?.userName.isNullOrBlank() || data?.password.isNullOrBlank()) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.signIn(data!!)
    }

    @Operation(
        summary = "Criar Login e token", description = "Criar Login e token",
        tags = ["Auth"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ResponseEntity::class))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
        ]
    )
    @PostMapping(value = ["/signuptoken"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
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
    @Operation(
        summary = "Criar Login", description = "Criar Login",
        tags = ["Auth"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ResponseEntity::class))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
        ]
    )
    @PostMapping(value = ["/signup"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
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

    @Operation(
        summary = "Refresh", description = "Atualizar Token",
        tags = ["Auth"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = ResponseEntity::class))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "500", content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
        ]
    )
    @PostMapping(value = ["/refresh/{username}"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun refreshToken(
        @PathVariable("username") username: String?,
        @RequestHeader("Authorization") refreshToken: String?
    ): ResponseEntity<*> {
        return if (refreshToken.isNullOrBlank() || username.isNullOrBlank()) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request")
        } else authService.refreshToken(username, refreshToken)
    }

}