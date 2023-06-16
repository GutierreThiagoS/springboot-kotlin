package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.services.ItemQuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/itemquestion")
class ItemQuestionController {

    @Autowired
    private lateinit var service: ItemQuestionService

    @GetMapping
    fun getAll(): List<ItemQuestion>{
        return service.getAll()
    }

    @PostMapping
    fun create(@RequestBody itemQuestion: ItemQuestion): ItemQuestion {
        return service.insert(itemQuestion)
    }

    @PostMapping("/all")
    fun insertAll(@RequestBody items: List<ItemQuestion>): List<ItemQuestion> {
        return service.insertAll(items)
    }

}