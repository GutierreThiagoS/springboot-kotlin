package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.model.ItemQuestion
import br.com.gutierre.productsdb.repositories.ItemQuestionRepository
import br.com.gutierre.productsdb.repositories.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ItemQuestionService {

    @Autowired
    private lateinit var repository: ItemQuestionRepository

    @Autowired
    private lateinit var repositoryQuestion: QuestionRepository

    private val logger = Logger.getLogger(ItemQuestionService::class.java.name)


    fun getAll(): List<ItemQuestion> {
        return repository.findAll()
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

        if (repositoryQuestion.findById(itemQuestion.idQuestion) == null)
            throw RequiredObjectIsNullException("Pergunta ainda não consta na base de dados!")

        return itemQuestion
    }
}