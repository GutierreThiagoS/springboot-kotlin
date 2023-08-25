package br.com.gutierre.productsdb.utils

import br.com.gutierre.productsdb.enums.Permission

fun stringOffice(office: Int?): String {
    return when (office) {
        Permission.NORMAL.id -> Permission.NORMAL.value
        Permission.ADMIN.id -> Permission.ADMIN.value
        else -> "Convidado"
    }
}
