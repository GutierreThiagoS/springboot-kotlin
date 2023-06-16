package br.com.gutierre.productsdb.controllers

import br.com.gutierre.productsdb.model.Group
import br.com.gutierre.productsdb.services.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/grupo/v1")
class GroupController {

    @Autowired
    private lateinit var service: GroupService


    @GetMapping
    fun getAll(): List<Group> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun findGroup(@PathVariable(value = "id") id: Long): Group {
        return service.findById(id)
    }

    @GetMapping("/groupid/{id}")
    fun findGroup(@PathVariable(value = "id") id: String): Group {
        return service.findGroupId(id)
    }

    @PostMapping
    fun create(@RequestBody group: Group): Group {
        return service.insert(group)
    }

    @PutMapping
    fun update(@RequestBody group: Group): Group {
        return service.update(group)
    }
}