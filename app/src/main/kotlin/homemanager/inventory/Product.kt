package homemanager.inventory

import java.util.*

data class Product(
        val id: String = null ?: UUID.randomUUID().toString(),
        val barcode: String = "",
        var name: String = "",
        var description: String = "",
        var price: String = ""
)