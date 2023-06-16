package br.com.gutierre.productsdb.model.response

import br.com.gutierre.productsdb.model.User

data class ResponseLogin(
    val info: String,
    val user: User,
    val permission: String,
    val status: Boolean = true
)
