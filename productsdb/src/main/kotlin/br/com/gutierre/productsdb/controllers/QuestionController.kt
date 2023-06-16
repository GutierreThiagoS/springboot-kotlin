package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.Question
import br.com.gutierre.productsdb.model.response.ResponseQuestions
import br.com.gutierre.productsdb.services.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/question")
class QuestionController {

    @Autowired
    private lateinit var service: QuestionService

    @GetMapping
    fun getAll(): List<Question> {
        return service.getAll()
    }

    @PostMapping
    fun create(@RequestBody question: Question): Question {
        return service.insert(question)
    }

    @GetMapping("/items")
    fun getAllQuestionsAndItems(): List<ResponseQuestions> {
        return service.getAllQuestionsAndItems()
    }
}