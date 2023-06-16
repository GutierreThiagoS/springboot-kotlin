package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.Product
import br.com.gutierre.productsdb.services.ProductsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/product/v1")
class ProductsController {

    @Autowired
    private lateinit var service: ProductsService


    @GetMapping
    fun getAll(): List<Product> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun findProduct(@PathVariable(value = "id") id: Long): Product {
        return service.findById(id)
    }

    @PostMapping
    fun create(@RequestBody product: Product): Product {
        return service.insert(product)
    }

    @PutMapping
    fun update(@RequestBody product: Product): Product {
        return service.update(product)
    }

}