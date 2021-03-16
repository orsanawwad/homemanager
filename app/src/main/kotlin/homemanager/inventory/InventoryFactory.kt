package homemanager.inventory

import java.util.*

class InventoryFactory {

    fun CreateProduct(
            id: String = null ?: UUID.randomUUID().toString(),
            barcode: String,
            name: String,
            description: String,
            price: String): Product {
        return Product(id, barcode, name, description, price)
    }

    fun CreateStock(
            id: String = null ?: UUID.randomUUID().toString(),
            product: Product,
            expireDate: Long,
            quantity: Int) : Stock {
        return Stock(id, product, expireDate, quantity)
    }

    fun CreateInventory(
            id: String? = null ?: UUID.randomUUID().toString(),
            products: MutableList<Product> = null ?: LinkedList(),
            stocks: MutableList<Stock> = null ?: LinkedList(),
            recipes: MutableList<Recipe> = null ?: LinkedList()
    ): Inventory {
        return Inventory(id, products, stocks, recipes)
    }

    fun CreateRecipeBuilder(): Recipe.RecipeBuilder {
        return Recipe.RecipeBuilder()
    }
}