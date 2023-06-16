package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.model.Question
import br.com.gutierre.productsdb.model.response.ResponseQuestions
import br.com.gutierre.productsdb.repositories.ItemQuestionRepository
import br.com.gutierre.productsdb.repositories.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger
import kotlin.math.log

@Service
class QuestionService {

    @Autowired
    private lateinit var repository: QuestionRepository

    @Autowired
    private lateinit var repositoryItem: ItemQuestionRepository

    private val logger = Logger.getLogger(QuestionService::class.java.name)


    fun getAll(): List<Question> {

        val lista  = repository.findAll()

        logger.info("$lista  ")

        return repository.findAll()
    }

    fun insert(question: Question?): Question {
        logger.info("Create one question with name ${question?.description}")

        val questionNotNull = questionIsNullOrValuesBlankException(question)

        return repository.save(questionNotNull)
    }

    fun findById(id: Long): Question {
        logger.info("Find Item Question with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID Question!") }
    }

    fun getAllQuestionsAndItems(): List<ResponseQuestions> {
        val questions: List<ResponseQuestions> = repository.findAll().map {
            ResponseQuestions(
                question = it,
                items = repositoryItem.getItemsInQuestion(it.id)
            )
        }

        return questions
    }



    private fun questionIsNullOrValuesBlankException(question: Question?): Question {

        if (question == null) throw RequiredObjectIsNullException()

        question.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'descrição' esta Vázia!")
                title.isBlank() -> throw RequiredObjectIsNullException("O Campo 'titulo' esta Vázia!")
            }
        }

        return question
    }
}