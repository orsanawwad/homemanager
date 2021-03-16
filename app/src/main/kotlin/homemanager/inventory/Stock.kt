package homemanager.inventory

import java.util.*
import kotlin.math.exp

data class Stock(
        val id: String = null ?: UUID.randomUUID().toString(),
        val product: Product,
        val expireDate: Long,
        var quantity: Int
)   