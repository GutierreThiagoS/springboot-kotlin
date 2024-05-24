package br.com.gutierre.productsdb.utils

import org.springframework.data.domain.Sort

fun String.sortDirection(): Sort.Direction {
    return if("desc".equals(this, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
}
