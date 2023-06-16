package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.SubGroup
import br.com.gutierre.productsdb.services.SubGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/subgrupo/v1")
class SubGroupController {

    @Autowired
    private lateinit var service: SubGroupService


    @GetMapping
    fun getAll(): List<SubGroup> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun findSubGroup(@PathVariable(value = "id") id: Long): SubGroup {
        return service.findById(id)
    }

    @GetMapping("/subgrupoid/{id}")
    fun findSubGroupId(@PathVariable(value = "id") id: String): SubGroup {
        return service.findSubGroupId(id)
    }

    @PostMapping
    fun create(@RequestBody subGroup: SubGroup): SubGroup {
        return service.insert(subGroup)
    }

    @PutMapping
    fun update(@RequestBody subGroup: SubGroup): SubGroup {
        return service.update(subGroup)
    }
}