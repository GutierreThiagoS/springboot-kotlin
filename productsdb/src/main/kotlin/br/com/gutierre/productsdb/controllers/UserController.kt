package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.User
import br.com.gutierre.productsdb.services.UserService
import br.com.gutierre.productsdb.utils.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/v1")
@Tag(name = "Usuários", description = "Endpoints para Mapear Usuários")
class UserController {

    @Autowired
    lateinit var service: UserService


    @Operation(
        summary = "Buscar Usuários", description = "Buscar todos Usuários",
        tags = ["Usuários"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = User::class)))
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
    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun getAll(): List<User> {
        return service.getAll()
    }

    @Operation(
        summary = "Desabilitar Usuário", description = "Desabilitar Usuário pelo id",
        tags = ["Usuários"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = User::class))
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
    @PatchMapping(value = ["/disable/{id}"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun disableUserById(
        @PathVariable("id") id: Long
    ): User {
        return id.let { service.disableUser(it) }// ?: throw Exception("Id esta Nulo")
    }
}