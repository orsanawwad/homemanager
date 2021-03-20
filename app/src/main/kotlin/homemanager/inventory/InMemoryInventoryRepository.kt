package homemanager.inventory

import kotlin.collections.HashMap

class InMemoryInventoryRepository(
        products: HashMap<String, HashMap<String, Product>>? = null,
        stocks: HashMap<String, HashMap<String, Stock>>? = null,
        recipes: HashMap<String, HashMap<String, Recipe>>? = null
): IInventoryRepository {//, IInventorySearchFilter {

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

    override fun GetAllProducts(userId: String): List<Product>? {
        return products[userId]?.values?.toList()
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

    override fun GetAllStocks(userId: String): List<Stock>? {
        return stocks[userId]?.values?.toList()
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

    override fun GetAllRecipe(userId: String): List<Recipe>? {
        return recipes[userId]?.values?.toList()
    }

    override fun UpdateRecipe(recipe: Recipe, userId: String) {
        recipes[userId]?.put(recipe.id,recipe)
    }

    override fun DeletRecipe(recipe: Recipe, userId: String) {
        recipes[userId]?.remove(recipe.id)
    }

//    override fun SearchProductFilter(userId: String, searchFilter: ISearchFilterPredicate<Product>): List<Product>? {
//        if (this.products[userId] == null) return null
//        //TODO: Refactor
//        val products = this.products[userId]?.values
//        if (products != null) {
//            val result = ArrayList<Product>()
//            for (product in products) {
//                if(searchFilter.Search(product)) {
//                    result.add(product)
//                }
//            }
//            return result
//        }
//        return null
//    }
//
//    override fun SearchStockFilter(userId: String, searchFilter: ISearchFilterPredicate<Stock>): List<Stock>? {
//        if (this.stocks[userId] == null) return null
//        val stocks = this.stocks[userId]?.values
//        if (stocks != null) {
//            val result = ArrayList<Stock>()
//            for (stock in stocks) {
//                if(searchFilter.Search(stock)) {
//                    result.add(stock)
//                }
//            }
//            return result
//        }
//        return null
//    }
//
//    override fun SearchRecipeFilter(userId: String, searchFilter: ISearchFilterPredicate<Recipe>): List<Recipe>? {
//        if (this.recipes[userId] == null) return null
//        val recipes = this.recipes[userId]?.values
//        if (recipes != null) {
//            val result = ArrayList<Recipe>()
//            for (recipe in recipes) {
//                if(searchFilter.Search(recipe)) {
//                    result.add(recipe)
//                }
//            }
//            return result
//        }
//        return null
//    }


}