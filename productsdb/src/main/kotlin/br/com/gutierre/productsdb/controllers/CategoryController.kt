package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.Category
import br.com.gutierre.productsdb.model.Product
import br.com.gutierre.productsdb.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categoria/v1")
class CategoryController {

    @Autowired
    private lateinit var service: CategoryService


    @GetMapping
    fun getAll(): List<Category> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun findCategory(@PathVariable(value = "id") id: Long): Category {
        return service.findById(id)
    }

    @PostMapping
    fun create(@RequestBody category: Category): Category {
        return service.insert(category)
    }
}