package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.Category
import br.com.gutierre.productsdb.services.CategoryService
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
@RequestMapping("/api/categoria/v1")
@Tag(name = "Categorias", description = "Endpoints para Mapear Categorias de Produtos")
class CategoryController {

    @Autowired
    private lateinit var service: CategoryService

    @Operation(
        summary = "Buscar Categorias", description = "Buscar todos Categorias dos Produtos",
        tags = ["Categorias"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(array = ArraySchema(schema = Schema(implementation = Category::class)))
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
    fun getAll(): List<Category> {
        return service.getAll()
    }

    @Operation(
        summary = "Buscar Categoria", description = "Buscar uma Categoria de Produtos pelo id",
        tags = ["Categorias"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = Category::class))
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
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun findCategory(@PathVariable(value = "id") id: Long): Category {
        return service.findById(id)
    }

    @Operation(
        summary = "Criar Categoria", description = "Criar uma Categoria de Produtos",
        tags = ["Categorias"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [
                    Content(schema = Schema(implementation = Category::class))
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
    @PostMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML])
    fun create(@RequestBody category: Category): Category {
        return service.insert(category)
    }
}