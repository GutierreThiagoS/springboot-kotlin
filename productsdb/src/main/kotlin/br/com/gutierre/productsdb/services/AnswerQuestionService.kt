package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.model.AnswerQuestion
import br.com.gutierre.productsdb.repositories.AnswerQuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AnswerQuestionService {

    @Autowired
    private lateinit var repository: AnswerQuestionRepository

    private val logger = Logger.getLogger(AnswerQuestionService::class.java.name)

    fun getAll(): List<AnswerQuestion> {
        return repository.findAll()
    }

    fun insert(answerQuestion: AnswerQuestion?): AnswerQuestion {
        logger.info("Create one Resposta with name ${answerQuestion?.description}")

        val answerNotNull = answerIsNullOrValuesBlankException(answerQuestion)

        if (repository.findAnswer(answerNotNull.questionId, answerNotNull.questionItemId) != null)
            throw RequiredObjectIsNullException("Resposta já existe na base de dados!")

        return repository.save(answerNotNull)
    }

    private fun answerIsNullOrValuesBlankException(answerQuestion: AnswerQuestion?): AnswerQuestion {

        if (answerQuestion == null) throw RequiredObjectIsNullException()

        answerQuestion.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'description' esta Vázia!")
                questionId == 0L -> throw RequiredObjectIsNullException("O Campo 'questionId' esta Vázia!")
                questionItemId == 0L -> throw RequiredObjectIsNullException("O Campo 'questionItemId' esta Vázia!")
            }
        }

        if (repository.findQuestion(answerQuestion.questionId) == null)
            throw RequiredObjectIsNullException("Pergunta não existe na base de dados!")

        if (repository.findItemQuestion(answerQuestion.questionId, answerQuestion.questionItemId) == null)
            throw RequiredObjectIsNullException("Item da Pergunta não existe na base de dados!")

        return answerQuestion
    }
}