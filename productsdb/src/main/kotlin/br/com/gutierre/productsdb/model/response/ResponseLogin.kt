package br.com.gutierre.productsdb.model.response

import br.com.gutierre.productsdb.model.UserQuest

data class ResponseLogin(
    val info: String,
    val user: UserQuest,
    val permission: String,
    val status: Boolean = true
)
