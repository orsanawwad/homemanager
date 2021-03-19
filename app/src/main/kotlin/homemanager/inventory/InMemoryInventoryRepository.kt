package homemanager.inventory

import java.util.ArrayList
import kotlin.collections.HashMap

class InMemoryInventoryRepository(
        products: HashMap<String, HashMap<String, Product>>? = null,
        stocks: HashMap<String, HashMap<String, Stock>>? = null,
        recipes: HashMap<String, HashMap<String, Recipe>>? = null
): IInventoryRepository, IInventorySearchStrategy {

    private val products: HashMap<String, HashMap<String, Product>> = products ?: HashMap()
    private val stocks: HashMap<String, HashMap<String, Stock>> = stocks ?: HashMap()
    private val recipes: HashMap<String, HashMap<String, Recipe>> = recipes ?: HashMap()
    override fun InitForUserID(userId: String) {
        products[userId] = HashMap()
        stocks[userId] = HashMap()
        recipes[userId] = HashMap()
    }

    override fun AddProduct(product: Product, userId: String) {
        if (!products.containsKey(userId)) return
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
        if (!stocks.containsKey(userId)) return
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
        if (!recipes.containsKey(userId)) return
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

    override fun SearchProductStrategy(userId: String, searchStrategy: ISearchStrategyPredicate<Product>): List<Product>? {
        if (this.products[userId] == null) return null
        val products = this.products[userId]?.values
        if (products != null) {
            val result = ArrayList<Product>()
            for (product in products) {
                if(searchStrategy.Search(product)) {
                    result.add(product)
                }
            }
            return result
        }
        return null
    }

    override fun SearchStockStrategy(userId: String, searchStrategy: ISearchStrategyPredicate<Stock>): List<Stock>? {
        if (this.stocks[userId] == null) return null
        val stocks = this.stocks[userId]?.values
        if (stocks != null) {
            val result = ArrayList<Stock>()
            for (stock in stocks) {
                if(searchStrategy.Search(stock)) {
                    result.add(stock)
                }
            }
            return result
        }
        return null
    }

    override fun SearchRecipeStrategy(userId: String, searchStrategy: ISearchStrategyPredicate<Recipe>): List<Recipe>? {
        if (this.recipes[userId] == null) return null
        val recipes = this.recipes[userId]?.values
        if (recipes != null) {
            val result = ArrayList<Recipe>()
            for (recipe in recipes) {
                if(searchStrategy.Search(recipe)) {
                    result.add(recipe)
                }
            }
            return result
        }
        return null
    }


}