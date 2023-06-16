package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.SubGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SubGroupRepository: JpaRepository<SubGroup, Long?> {

    @Query(value = "SELECT * FROM sub_group WHERE subgrupo_id = :subgroupId LIMIT 1", nativeQuery = true)
    fun findSubGroupId(
        @Param("subgroupId") subgroupId: String
    ): SubGroup?

    @Query(value = "SELECT * FROM sub_group WHERE subgrupo_id = :subgroupId AND filial = :filial LIMIT 1", nativeQuery = true)
    fun findSubGroupIdInFilial(
        @Param("subgroupId") subgroupId: String,
        @Param("filial") filial: String
    ): SubGroup?
}