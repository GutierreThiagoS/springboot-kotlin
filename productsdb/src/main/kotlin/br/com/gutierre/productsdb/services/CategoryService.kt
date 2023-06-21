package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.Category
import br.com.gutierre.productsdb.model.Product
import br.com.gutierre.productsdb.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class CategoryService {

    @Autowired
    private lateinit var repository: CategoryRepository

    private val logger = Logger.getLogger(CategoryService::class.java.name)
    fun getAll(): List<Category> {
        return repository.findAll()
    }

    fun findById(id: Long): Category {
        logger.info("Create one categoria with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun insert(category: Category?): Category {

        logger.info("")

        logger.info("Create one grupo with name ${category?.description}")

        val categoryNotNull = categoryIsNullOrValuesBlankException(category)

        if (repository.findCategoryName(categoryNotNull.description, categoryNotNull.filial) != null)
            throw RequiredObjectIsNullException("Categoria já existe na base de dados existe!")

        return repository.save(categoryNotNull)
    }


    private fun categoryIsNullOrValuesBlankException(category: Category?): Category {

        if (category == null) throw RequiredObjectIsNullException()

        category.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'description' esta Vázia!")
                filial.isBlank() -> throw RequiredObjectIsNullException("O Campo 'filial' esta Vázia!")
            }
        }

        return category
    }
}