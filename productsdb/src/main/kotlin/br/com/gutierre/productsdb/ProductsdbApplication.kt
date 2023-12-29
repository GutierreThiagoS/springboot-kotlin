package br.com.gutierre.productsdb

import br.com.gutierre.productsdb.utils.encryptPass
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ProductsdbApplication

fun main(args: Array<String>) {
	runApplication<ProductsdbApplication>(*args)

	encryptPass("12345678")
//	362ad02420268beeb22d3a1f0d92749df461d7f4b74c9433d7415bdeef1b2902f4eb1edaecb37cb3
}
