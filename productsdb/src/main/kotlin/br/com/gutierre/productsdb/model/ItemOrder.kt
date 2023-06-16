package br.com.gutierre.productsdb.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class ItemOrder(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "order_id", nullable = false, length = 10)
    var orderId: Long = 0,

    @Column(name = "product_id", nullable = false, length = 10)
    var productId: Long = 0,

    @Column(nullable = false, length = 2)
    var filial: String = "",

    @Column(name = "quantidade", nullable = false)
    var quantity: Int = 0,

    @Column(name = "preco", nullable = false)
    var price: Float = 0f,

    @Column(name = "preco_total", nullable = false)
    var priceTotal: Float = 0f,

    @Column(name = "item_data")
    var itemDate: String = "",

    @Column(name = "time_stamp")
    var timeStamp: Date? = null,

)
