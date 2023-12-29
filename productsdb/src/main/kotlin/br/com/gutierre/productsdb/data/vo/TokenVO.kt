package br.com.gutierre.productsdb.data.vo

import java.util.Date

data class TokenVO(
    val info: String = "Logado com Sucesso!",
    val userName: String? = null,
    val authenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null
)
