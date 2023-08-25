package br.com.gutierre.productsdb.services.questions

import br.com.gutierre.productsdb.exceptions.RequiredObjectIsNullException
import br.com.gutierre.productsdb.exceptions.ResourceNotFoundException
import br.com.gutierre.productsdb.model.UserQuest
import br.com.gutierre.productsdb.model.request.RequestLogin
import br.com.gutierre.productsdb.model.request.RequestRegister
import br.com.gutierre.productsdb.model.response.ResponseInfo
import br.com.gutierre.productsdb.model.response.ResponseLogin
import br.com.gutierre.productsdb.repositories.question.UserQuestRepository
import br.com.gutierre.productsdb.utils.stringOffice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserQuestService {

    @Autowired
    private lateinit var repository: UserQuestRepository

    private val logger = Logger.getLogger(UserQuestService::class.java.name)

    fun getAll(): List<UserQuest> {
        logger.info("Finding all usuario")

        return repository.findAll()
    }

    fun findById(id: Long): UserQuest {
        logger.info("Find usuario with id $id")

        return repository.findById(id).orElseThrow { ResourceNotFoundException("No Records found for this ID!") }
    }

    fun insert(request: RequestRegister?): ResponseInfo {
        logger.info("Create one question with name ${request?.email}")

        val requestNotNull = userIsNullOrValuesBlankException(request)

        if (repository.findUserEmail(requestNotNull.email) != null)
            throw RequiredObjectIsNullException("já existe usuário com Email: ${requestNotNull.email}!")

        val user = repository.save(
                UserQuest(
                    name = requestNotNull.name,
                    email = requestNotNull.email,
                    password = requestNotNull.password,
//                    office = if (requestNotNull.admin) 1 else 0
                )
        )

        return ResponseInfo(info = "${user.name} cadastrado com sucesso!")
    }

    fun login(request: RequestLogin?): ResponseLogin {
        logger.info("Logando com email ${request?.email}")

        if (request == null) throw RequiredObjectIsNullException("Usuário ou Senha incorreta!")

        val user = repository.findUserLogin(request.email, request.password)
            ?: throw RequiredObjectIsNullException("Usuário ou Senha incorreta!")

        return ResponseLogin(
            info = "Logado com Sucesso!",
            user = user,
            permission = stringOffice(user.office)
        )
    }

    private fun userIsNullOrValuesBlankException(request: RequestRegister?): RequestRegister {

        if (request == null) throw RequiredObjectIsNullException()

        request.apply {
            when {
                email.isBlank() -> throw RequiredObjectIsNullException("O Campo 'email' esta Vázia!")
                password.isBlank() -> throw RequiredObjectIsNullException("O Campo 'senha' esta Vázia!")
                name.isBlank() -> throw RequiredObjectIsNullException("O Campo 'nome' esta Vázia!")
            }
        }

        return request
    }
}