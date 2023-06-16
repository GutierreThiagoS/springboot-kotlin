package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.enums.Permission
import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.User
import br.com.gutierre.productsdb.model.request.RequestLogin
import br.com.gutierre.productsdb.model.response.ResponseLogin
import br.com.gutierre.productsdb.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService {

    @Autowired
    private lateinit var repository: UserRepository

    private val logger = Logger.getLogger(UserService::class.java.name)


    fun getAll(): List<User> {
        logger.info("Finding all usuario")

        return repository.findAll()
    }

    fun findById(id: Long): User {
        logger.info("Find usuario with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun insert(user: User?): User {
        logger.info("Create one question with name ${user?.email}")

        val userNotNull = userIsNullOrValuesBlankException(user)

        if (repository.findUserEmail(userNotNull.email) != null)
            throw RequiredObjectIsNullException("já existe usuario com Email: ${userNotNull.email}!")

        return repository.save(userNotNull)
    }

    fun login(request: RequestLogin?): ResponseLogin {
        logger.info("Logando com email ${request?.email}")

        if (request == null) throw RequiredObjectIsNullException("Usuário ou Senha incorreta!")

        val user = repository.findUserLogin(request.email, request.password)
            ?: throw RequiredObjectIsNullException("Usuário ou Senha incorreta!")

        return ResponseLogin(
            info = "Logado com Sucesso!",
            user = user,
            permission =
                when (user.office) {
                    Permission.NORMAL.id -> Permission.NORMAL.value
                    Permission.ADMIN.id -> Permission.ADMIN.value
                    else -> ""
                }
        )
    }

    private fun userIsNullOrValuesBlankException(user: User?): User {

        if (user == null) throw RequiredObjectIsNullException()

        user.apply {
            when {
                email.isBlank() -> throw RequiredObjectIsNullException("O Campo 'email' esta Vázia!")
                password.isBlank() -> throw RequiredObjectIsNullException("O Campo 'senha' esta Vázia!")
                name.isBlank() -> throw RequiredObjectIsNullException("O Campo 'nome' esta Vázia!")
            }
        }

        return user
    }
}