package br.com.gutierre.productsdb.enums

enum class Permission(val id: Int, val value: String) {
    NORMAL(0, "Normal"),
    ADMIN(1, "Admin")
}