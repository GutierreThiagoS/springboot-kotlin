package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GroupRepository: JpaRepository<Group, Long?> {

    @Query(value = "SELECT * FROM Grupo WHERE grupo_id = :groupId LIMIT 1", nativeQuery = true)
    fun findGroupId(
        @Param("groupId") groupId: String
    ): Group?

    @Query(value = "SELECT * FROM Grupo WHERE grupo_id = :groupId AND filial = :filial LIMIT 1", nativeQuery = true)
    fun findGroupIdInFilial(
        @Param("groupId") groupId: String,
        @Param("filial") filial: String
    ): Group?
}