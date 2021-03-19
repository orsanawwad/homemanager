package homemanager.inventory

import homemanager.auth.User

//Current assumption, each user can only have one inventory
interface IInventoryRepository {
    //Add all CRUD operations
    // Create
    // Read
    // Update
    // Delete
    fun InitForUserID(userId: String)

    fun AddProduct(product: Product, userId: String)
    fun GetProduct(productId: String, userId: String): Product?
    fun UpdateProduct(product: Product, userId: String)
    fun DeletProduct(product: Product, userId: String)

    fun AddStock(stock: Stock, userId: String)
    fun GetStock(stockId: String, userId: String): Stock?
    fun UpdateStock(stock: Stock, userId: String)
    fun DeletStock(stock: Stock, userId: String)

    fun AddRecipe(recipe: Recipe, userId: String)
    fun GetRecipe(recipeId: String, userId: String): Recipe?
    fun UpdateRecipe(recipe: Recipe, userId: String)
    fun DeletRecipe(recipe: Recipe, userId: String)

}