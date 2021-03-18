package homemanager.inventory

import java.util.*
import kotlin.collections.HashMap

//TODO: Add builder methods
enum class ProductMetadata {
    CALORIE,PRICE
}

class Product private constructor(
        val id: String = null ?: UUID.randomUUID().toString(),
        val barcode: String = "",
        var name: String = "",
        var description: String = "",
        var metadata: MutableMap<ProductMetadata,String> = null ?: EnumMap<ProductMetadata,String>(ProductMetadata::class.java) // SHRUGS
) {
    data class ProductBuilder(
            var id: String = null ?: UUID.randomUUID().toString(),
            var barcode: String = "",
            var name: String = "",
            var description: String = "",
            var metadata: MutableMap<ProductMetadata,String> = null ?: EnumMap<ProductMetadata,String>(ProductMetadata::class.java) // SHRUGS
    ) {
        fun ID(id: String) = apply { this.id = id }
        fun Name(name: String) = apply { this.name = name }
        fun Barcode(barcode: String) = apply { this.barcode = barcode }
        fun Description(description: String) = apply { this.description=description }
        fun AddMetadata(productMetadata: ProductMetadata, data: String) = apply { this.metadata[productMetadata] = data }
        fun Build() = Product(id, barcode, name, description, metadata)
    }
}