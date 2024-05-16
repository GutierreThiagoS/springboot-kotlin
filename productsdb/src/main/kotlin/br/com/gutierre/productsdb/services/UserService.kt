package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.User
import br.com.gutierre.productsdb.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger

@Service
class UserService(
    @field:Autowired var repository: UserRepository
): UserDetailsService {

    private val logger = Logger.getLogger(UserService::class.java.name)

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Finding one User with Username $username!")
        val user = repository.findByUserName(username)
        return (user as UserDetails?) ?: throw UsernameNotFoundException("UserName $username not found")
    }

    fun getAll(): List<User> {
        logger.info("Disabling all user !")

        return repository.findAll().mapNotNull { it }
    }

    @Transactional
    fun disableUser(id: Long): User {
        logger.info("Disabling one user with ID $id!")

        repository.disableUser(id)

        val user = repository.findById(id)
            .orElseThrow {
                ResourceNotFoundException("No records found for this ")
            }

        return user ?: throw ResourceNotFoundException("No records found for this ")
    }
}