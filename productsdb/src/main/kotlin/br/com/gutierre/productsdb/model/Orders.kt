package br.com.gutierre.productsdb.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
data class Orders(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(nullable = false, length = 2)
    var filial: String = "",

    @Column(name = "descricao", nullable = false)
    var description: String = "",

    @Column(name = "preco_total", nullable = false)
    var priceTotal: Float = 0f,

    @Column(name = "order_data")
    var itemDate: String = "",

    @Column(name = "time_stamp")
    var timeStamp: Date? = null,

    @Column(name = "finalizado", nullable = false)
    var finish: Int = 0,
)
