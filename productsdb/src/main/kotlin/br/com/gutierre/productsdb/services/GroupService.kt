package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.Group
import br.com.gutierre.productsdb.repositories.GroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class GroupService {

    @Autowired
    private lateinit var repository: GroupRepository

    private val logger = Logger.getLogger(ProductsService::class.java.name)

    fun getAll(): List<Group> {
        logger.info("Finding all Grupos")

        return repository.findAll()
    }

    fun findById(id: Long): Group {
        logger.info("Find grupo with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun findGroupId(groupId: String): Group {
        logger.info("Find groupId with $groupId")

        return groupIsNullOrValuesBlankException(repository.findGroupId(groupId))
    }

    fun insert(group: Group?): Group {

        logger.info("Create one grupo with name ${group?.description}")

        val groupNotNull = groupIsNullOrValuesBlankException(group)

        if (repository.findGroupIdInFilial(groupNotNull.groupId, groupNotNull.filial) != null)
            throw RequiredObjectIsNullException("Categoria já existe na base de dados existe!")

        return repository.save(groupNotNull)
    }

    fun update(group: Group?): Group {
        logger.info("Update one grupo with name ${group?.description}")

        val groupNotNull = groupIsNullOrValuesBlankException(group)

        val entity: Group = repository.findById(groupNotNull.id)
            .orElseThrow { ResourceNotFoundException("No Records found for this ID!") }

        entity.description = groupNotNull.description
        entity.groupId = groupNotNull.groupId
        entity.filial = groupNotNull.filial
        entity.img = groupNotNull.img

        return repository.save(entity)
    }

    private fun groupIsNullOrValuesBlankException(group: Group?): Group {

        if (group == null) throw RequiredObjectIsNullException()

        group.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'description' esta Vázia!")
                groupId.isBlank() -> throw RequiredObjectIsNullException("O Campo 'grupoId' esta Vázia!")
                filial.isBlank() -> throw RequiredObjectIsNullException("O Campo 'filial' esta Vázia!")
            }
        }

        return group
    }
}