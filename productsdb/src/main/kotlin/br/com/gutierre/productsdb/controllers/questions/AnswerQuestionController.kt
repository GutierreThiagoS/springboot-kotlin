package br.com.gutierre.productsdb.controllers.questions

import br.com.gutierre.productsdb.model.AnswerQuestion
import br.com.gutierre.productsdb.model.request.RequestAnswerQuestion
import br.com.gutierre.productsdb.model.response.ResponseAnswerQuestion
import br.com.gutierre.productsdb.services.questions.AnswerQuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/resposta")
class AnswerQuestionController {

    @Autowired
    private lateinit var service: AnswerQuestionService

    @GetMapping
    fun getAll(): List<AnswerQuestion> {
        return service.getAll()
    }

    @PostMapping
    fun create(@RequestBody requestAnswerQuestion: RequestAnswerQuestion): ResponseAnswerQuestion {
        return service.insert(requestAnswerQuestion)
    }
}