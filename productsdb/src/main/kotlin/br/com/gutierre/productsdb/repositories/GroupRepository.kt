package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GroupRepository: JpaRepository<Group, Long?> {

    @Query(value = "SELECT G FROM Group G WHERE G.groupId = :groupId")
    fun findGroupId(
        @Param("groupId") groupId: String
    ): Group?

    @Query(value = "SELECT G FROM Group G WHERE G.groupId = :groupId AND G.filial = :filial")
    fun findGroupIdInFilial(
        @Param("groupId") groupId: String,
        @Param("filial") filial: String
    ): Group?
}