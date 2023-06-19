package br.com.gutierre.productsdb.services.questions

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.repositories.question.ItemQuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ItemQuestionService {

    @Autowired
    private lateinit var repository: ItemQuestionRepository

    @Autowired
    private lateinit var serviceQuestion: QuestionService

    private val logger = Logger.getLogger(ItemQuestionService::class.java.name)


    fun getAll(): List<ItemQuestion> {
        return repository.findAll()
    }

    fun findById(id: Long): ItemQuestion {
        logger.info("Find Item Question with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun insert(itemQuestion: ItemQuestion?): ItemQuestion {
        logger.info("Create one itemQuestion with name ${itemQuestion?.description}")

        val itemQuestionNotNull = itemQuestionIsNullOrValuesBlankException(itemQuestion)

        return repository.save(itemQuestionNotNull)
    }

    fun insertAll(items: List<ItemQuestion>?): List<ItemQuestion> {
        logger.info("Create one itemQuestion with name $items")

        if (items == null) throw RequiredObjectIsNullException()

        val itemsNotNUll = items.map { itemQuestionIsNullOrValuesBlankException(it) }

        return repository.saveAll(itemsNotNUll)
    }



    private fun itemQuestionIsNullOrValuesBlankException(itemQuestion: ItemQuestion?): ItemQuestion {

        if (itemQuestion == null) throw RequiredObjectIsNullException()

        itemQuestion.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'descrição' esta Vázia!")
                idQuestion == 0L-> throw RequiredObjectIsNullException("O Campo 'Pergunta' esta Vázia!")
            }
        }

        val id = itemQuestion.idQuestion

        serviceQuestion.findById(id)

        return itemQuestion
    }
}