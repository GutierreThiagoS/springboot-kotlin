package br.com.gutierre.productsdb.services.questions

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.model.AnswerQuestion
import br.com.gutierre.productsdb.model.Question
import br.com.gutierre.productsdb.model.request.RequestAnswerQuestion
import br.com.gutierre.productsdb.model.response.ResponseAnswerQuestion
import br.com.gutierre.productsdb.repositories.question.AnswerQuestionRepository
import br.com.gutierre.productsdb.repositories.question.ItemQuestionRepository
import br.com.gutierre.productsdb.repositories.question.QuestionRepository
import br.com.gutierre.productsdb.repositories.question.UserQuestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AnswerQuestionService {

    @Autowired
    private lateinit var repository: AnswerQuestionRepository

    @Autowired
    private lateinit var repositoryUser: UserQuestRepository

    @Autowired
    private lateinit var questionRepository: QuestionRepository

    @Autowired
    private lateinit var itemquestionRepository: ItemQuestionRepository

    private val logger = Logger.getLogger(AnswerQuestionService::class.java.name)

    fun getAll(): List<AnswerQuestion> {
        return repository.findAll()
    }

    fun insert(request: RequestAnswerQuestion?): ResponseAnswerQuestion {
        logger.info("Create one Resposta with name $request")

        val requestNotNull = answerIsNullOrValuesBlankException(request)

        val user = repositoryUser.findById(requestNotNull.userId)
                .orElseThrow { RequiredObjectIsNullException("Usuário não encontrado!") }

        val question: Question = questionRepository.findQuestion(requestNotNull.questionId, user.id)
                ?: throw RequiredObjectIsNullException("Pergunta não existe na base de dados!")

        val itemQuestion = itemquestionRepository.findItemQuestion(question.id, requestNotNull.questionItemId)
                ?: throw RequiredObjectIsNullException("Item da Pergunta não existe na base de dados!")

        if (repository.findAnswer(question.id, itemQuestion.id, userId = user.id) != null)
            throw RequiredObjectIsNullException("Essa resposta existe na base de dados!")

        val answerQuestion = repository.save(
                AnswerQuestion(
                        questionId = question.id,
                        questionItemId = itemQuestion.id,
                        description = itemQuestion.description,
                        userId = user.id
                )
        )

        return ResponseAnswerQuestion(
                title = question.title,
                description = question.description,
                answer = itemQuestion.description,
                responseDate = answerQuestion.timeResponse.toString(),
                userAnswer = user.name
        )
    }

    private fun answerIsNullOrValuesBlankException(request: RequestAnswerQuestion?): RequestAnswerQuestion {

        if (request == null) throw RequiredObjectIsNullException()

        request.apply {
            when {
                questionId == 0L -> throw RequiredObjectIsNullException("O Campo 'codigo_pergunta' esta Vázia!")
                questionItemId == 0L -> throw RequiredObjectIsNullException("O Campo 'codigo_opcao' esta Vázia!")
                userId == 0L -> throw RequiredObjectIsNullException("O Campo 'codigo_usuario' esta Vázia!")
            }
        }

        return request
    }
}