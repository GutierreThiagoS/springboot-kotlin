package br.com.gutierre.productsdb.controllers.questions

import br.com.gutierre.productsdb.model.Question
import br.com.gutierre.productsdb.model.request.RequestSaveQuestion
import br.com.gutierre.productsdb.model.response.ResponseQuestions
import br.com.gutierre.productsdb.model.response.ResponseSaveQuestion
import br.com.gutierre.productsdb.services.questions.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/items/{id}")
    fun getAllQuestionsAndItems(@PathVariable(value = "id") id: Long): List<ResponseQuestions> {
        return service.getAllQuestionsAndItems(id)
    }

    @PostMapping("/save")
    fun saveQuestionsAndItems(@RequestBody request: RequestSaveQuestion): ResponseSaveQuestion {
        return service.insert(request)
    }
}