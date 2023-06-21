package br.com.gutierre.productsdb.services.questions

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.model.Question
import br.com.gutierre.productsdb.model.User
import br.com.gutierre.productsdb.model.request.RequestQuestions
import br.com.gutierre.productsdb.model.request.RequestSaveQuestion
import br.com.gutierre.productsdb.model.response.ResponseQuestions
import br.com.gutierre.productsdb.model.response.ResponseSaveQuestion
import br.com.gutierre.productsdb.repositories.question.ItemQuestionRepository
import br.com.gutierre.productsdb.repositories.question.QuestionRepository
import br.com.gutierre.productsdb.repositories.question.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class QuestionService {

    @Autowired
    private lateinit var repository: QuestionRepository

    @Autowired
    private lateinit var repositoryItem: ItemQuestionRepository

    @Autowired
    private lateinit var repositoryUser: UserRepository

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

    fun insert(request: RequestSaveQuestion?): ResponseSaveQuestion {
        logger.info("Create one question with name ${request?.description}")

        val requestNotNull = questionIsNullOrValuesBlankException(request)

        val userId: Long = repositoryUser.findUserId(requestNotNull.userForQuestionId)?.id ?:
            if (requestNotNull.userForQuestionId == 0L) 0 else throw RequiredObjectIsNullException("Usuário destino não existe!")

        val question = repository.save(
                Question(
                        title = requestNotNull.title,
                        description = requestNotNull.description,
                        userId = userId
                )
        )

        val itemsQuestion = repositoryItem.saveAll(requestNotNull.items.map {
            ItemQuestion(
                    description = it.description,
                    idQuestion = question.id,
                    positionOrder = it.position
            )
        })

        return ResponseSaveQuestion(
                question = question,
                items = itemsQuestion
        )
    }



    fun findById(id: Long): Question {
        logger.info("Find Item Question with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID Question!") }
    }

    fun getAllQuestionsAndItems(request: RequestQuestions?): List<ResponseQuestions> {

        if (request == null) throw RequiredObjectIsNullException()
        if (request.limit == 0) throw RequiredObjectIsNullException("Limite deve ser maior que Zero!")

        val user = repositoryUser.findById(request.userId).orElseThrow { ResourceNotFoundException("Usuário não encontrado!") }

        val questions: List<ResponseQuestions> = repository.getAllQuestionUser(user.id, request.limit).map {
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

    private fun questionIsNullOrValuesBlankException(request: RequestSaveQuestion?): RequestSaveQuestion {

        if (request == null) throw RequiredObjectIsNullException()

        val user: User = repositoryUser.findById(request.adminId)
                .orElseThrow { ResourceNotFoundException("Usuário requerente não encontrado!") }

        request.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'descrição' esta Vázia!")
                title.isBlank() -> throw RequiredObjectIsNullException("O Campo 'titulo' esta Vázia!")
                items.isEmpty() -> throw RequiredObjectIsNullException("Não há 'opções' para pergunta!")
                user.office == 0 -> throw RequiredObjectIsNullException("Usuário não tem permissão!")
                items.any { it.description.isBlank() } -> throw RequiredObjectIsNullException("Existe opção vázia!")
            }
        }

        return request
    }
}