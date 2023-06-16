package br.com.gutierre.productsdb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductsdbApplication

fun main(args: Array<String>) {
	runApplication<ProductsdbApplication>(*args)
}
