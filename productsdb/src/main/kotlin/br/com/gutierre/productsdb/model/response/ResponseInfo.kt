package br.com.gutierre.productsdb.model.response

data class ResponseInfo(
        val info: String = "Dados salvo com sucesso!",
        val status: Boolean = true,
)
