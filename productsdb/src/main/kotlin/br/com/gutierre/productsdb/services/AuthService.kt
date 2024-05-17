package br.com.gutierre.productsdb.services

import br.com.gutierre.productsdb.data.vo.v1.AccountCredentialVO
import br.com.gutierre.productsdb.data.vo.v1.TokenVO
import br.com.gutierre.productsdb.model.request.RequestSignUp
import br.com.gutierre.productsdb.model.response.ResponseInfo
import br.com.gutierre.productsdb.model.response.ResponseSingIn
import br.com.gutierre.productsdb.repositories.UserRepository
import br.com.gutierre.productsdb.security.jwt.JwtTokenProvider
import br.com.gutierre.productsdb.utils.encryptPass
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class AuthService {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager
    @Autowired
    lateinit var tokenProvider: JwtTokenProvider
    @Autowired
    lateinit var repository: UserRepository

    private val logger = Logger.getLogger(AuthService::class.java.name)

    fun signIn(data: AccountCredentialVO): ResponseEntity<*> {
        return try {
            val username = data.userName
            val password = data.password
            val user = repository.findByUserName(username)
            println(user?.userName)
            println(user?.password)

            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))

            val tokenResponse: TokenVO = if(user != null && username != null) {
                tokenProvider.createAccessToken(username, user.roles)
            } else {
                throw UsernameNotFoundException("Username $username not found!")
            }
            ResponseEntity.ok(tokenResponse)
        } catch (e: AuthenticationException) {
            println("ERROR -> $e")
            if (data.platform == "flutter")
                return ResponseEntity.ok(ResponseSingIn("Usuário inexistente ou Senha inválido!"))
            throw BadCredentialsException("Invalid username or password supplied!")
        } catch (e: Exception) {
            println("ERROR -> $e")
            if (data.platform == "flutter")
                return ResponseEntity.ok(ResponseSingIn("Usuário inexistente ou Senha inválido!"))
            throw BadCredentialsException("Invalid username or password supplied!")
        }
    }

    fun signup(data: RequestSignUp): ResponseEntity<*> {
        return try {
            val user = repository.findByUserName(data.userName)

            val passEncryptor = encryptPass(data.password)
            println(passEncryptor?.replace("{pbkdf2}", ""))

            if (user == null && passEncryptor != null) {
                repository.insert(
                    userName = data.userName,
                    fullName = data.fullName,
                    email = data.email,
                    password = passEncryptor.replace("{pbkdf2}", "")
                )
                ResponseEntity.ok(ResponseInfo())
            } else {
//                throw UsernameNotFoundException("Username ${data.userName} existe!")
                ResponseEntity.ok(ResponseInfo("Login ${data.userName} existe! \nNão foi possivel cadastrar usuário!", false))
            }
        } catch (e: Exception) {
            println("ERROR -> $e")
            if (data.platform == "flutter")
                return ResponseEntity.ok(ResponseInfo("Não foi possivel cadastrar usuário! \n$e", false))
            throw BadCredentialsException("Invalid username or password supplied!")
        }
    }

    fun refreshToken(username: String, refreshToken: String): ResponseEntity<*> {
        logger.info("trying get refresh token to user $username")

        val user = repository.findByUserName(username)
        val tokenResponse: TokenVO = if(user != null) {
            tokenProvider.refreshToken(refreshToken)
        } else {
            throw UsernameNotFoundException("Username $username not found!")
        }
        return ResponseEntity.ok(tokenResponse)
    }
}