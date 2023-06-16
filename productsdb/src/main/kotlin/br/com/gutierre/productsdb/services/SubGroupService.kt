package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.SubGroup
import br.com.gutierre.productsdb.repositories.GroupRepository
import br.com.gutierre.productsdb.repositories.SubGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class SubGroupService {


    @Autowired
    private lateinit var repository: SubGroupRepository

    @Autowired
    private lateinit var repositoryGroup: GroupRepository

    private val logger = Logger.getLogger(ProductsService::class.java.name)


    fun getAll(): List<SubGroup> {
        logger.info("Finding all SubGroups")

        return repository.findAll()
    }

    fun findById(id: Long): SubGroup {
        logger.info("Find SubGroup with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun findSubGroupId(id: String): SubGroup {
        logger.info("Find subgroupId $id")

        return subGroupIsNullOrValuesBlankException(repository.findSubGroupId(id))
    }

    fun insert(subGroup: SubGroup?): SubGroup {

        logger.info("Create one product with name ${subGroup?.description}")

        val subGroupNotNull = subGroupIsNullOrValuesBlankException(subGroup)

        if (repository.findSubGroupIdInFilial(subGroupNotNull.subGroupId, subGroupNotNull.filial) != null)
            throw RequiredObjectIsNullException("Segmento já existe na base de dados existe!")

        return repository.save(subGroupNotNull)
    }

    fun update(subGroup: SubGroup?): SubGroup {
        logger.info("Update one SubGroup with name ${subGroup?.description}")

        val subGroupNotNull = subGroupIsNullOrValuesBlankException(subGroup)

        val entity: SubGroup = repository.findById(subGroupNotNull.id)
            .orElseThrow { ResourceNotFoundException("No Records found for this ID!") }

        entity.description = subGroupNotNull.description
        entity.groupId = subGroupNotNull.groupId
        entity.subGroupId = subGroupNotNull.subGroupId
        entity.filial = subGroupNotNull.filial
        entity.img = subGroupNotNull.img

        return repository.save(entity)
    }

    private fun subGroupIsNullOrValuesBlankException(subGroup: SubGroup?): SubGroup {

        if (subGroup == null) throw RequiredObjectIsNullException()

        subGroup.apply {
            when {
                description.isBlank() -> throw RequiredObjectIsNullException("O Campo 'description' esta Vázia!")
                groupId.isBlank() -> throw RequiredObjectIsNullException("O Campo 'grupoId' esta Vázia!")
                subGroupId.isBlank() -> throw RequiredObjectIsNullException("O Campo 'subGrupoId' esta Vázia!")
                filial.isBlank() -> throw RequiredObjectIsNullException("O Campo 'filial' esta Vázia!")
            }
        }

        if (repositoryGroup.findGroupId(subGroup.groupId) == null)
            throw RequiredObjectIsNullException("A categoria de código ${subGroup.groupId} não existe!")

        return subGroup
    }
}