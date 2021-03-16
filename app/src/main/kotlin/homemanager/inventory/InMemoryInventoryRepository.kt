package homemanager.inventory

import kotlin.collections.HashMap

class InMemoryInventoryRepository(
        products: HashMap<String, HashMap<String, Product>>? = null,
        stocks: HashMap<String, HashMap<String, Stock>>? = null,
        recipes: HashMap<String, HashMap<String, Recipe>>? = null
): IInventoryRepository {

    val products: HashMap<String, HashMap<String, Product>> = products ?: HashMap()
    val stocks: HashMap<String, HashMap<String, Stock>> = stocks ?: HashMap()
    val recipes: HashMap<String, HashMap<String, Recipe>> = recipes ?: HashMap()


    override fun AddProduct(product: Product, userId: String) {
        if (!products.containsKey(userId)) {
            products[userId] = HashMap()
        }
        products[userId]?.put(product.id,product)
    }

    override fun GetProduct(productId: String, userId: String): Product? {
        return products[userId]?.get(productId)
    }

    override fun UpdateProduct(product: Product, userId: String) {
        products[userId]?.put(product.id,product)
    }

    override fun DeletProduct(product: Product, userId: String) {
        products[userId]?.remove(product.id)
    }

    override fun AddStock(stock: Stock, userId: String) {
        if (!stocks.containsKey(userId)) {
            stocks[userId] = HashMap()
        }
        stocks[userId]?.put(stock.id,stock)
    }

    override fun GetStock(stockId: String, userId: String): Stock? {
        return stocks[userId]?.get(stockId)
    }

    override fun UpdateStock(stock: Stock, userId: String) {
        stocks[userId]?.put(stock.id,stock)
    }

    override fun DeletStock(stock: Stock, userId: String) {
        stocks[userId]?.remove(stock.id)
    }

    override fun AddRecipe(recipe: Recipe, userId: String) {
        if (!recipes.containsKey(userId)) {
            recipes[userId] = HashMap()
        }
        recipes[userId]?.put(recipe.id,recipe)
    }

    override fun GetRecipe(recipeId: String, userId: String): Recipe? {
        return recipes[userId]?.get(recipeId)
    }

    override fun UpdateRecipe(recipe: Recipe, userId: String) {
        recipes[userId]?.put(recipe.id,recipe)
    }

    override fun DeletRecipe(recipe: Recipe, userId: String) {
        recipes[userId]?.remove(recipe.id)
    }


}