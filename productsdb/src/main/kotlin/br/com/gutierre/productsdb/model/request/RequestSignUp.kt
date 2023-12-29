package br.com.gutierre.productsdb.model.request

data class RequestSignUp(

    var userName: String = "",

    var fullName: String = "",

    var email: String = "",

    var password: String = "",

    val platform: String = "",
)