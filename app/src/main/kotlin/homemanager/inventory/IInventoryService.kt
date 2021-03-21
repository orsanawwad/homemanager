package homemanager.inventory

import homemanager.auth.User

// Add dependency to user service to confirm if a user exists
interface IInventoryService {

    //TODO: Add other operations

    fun AddProduct(product: Product, user: User)

//    fun DeleteProduct(productId: String, user: User)

//    fun UpdateProduct(productId: String, user: User)

    fun SearchProduct(keyword: String, user: User): List<Product>?

    fun GetAllProducts(user: User): List<Product>?

    fun AddStock(stock: Stock, user: User)

    fun UpdateStock(stock: Stock, user: User)

//    fun DeleteStock(stock: Stock, user: User)

    fun GetStocksForProduct(productId: String, user: User): List<Stock>?

    fun AddRecipe(recipe: Recipe, user: User)

    fun UpdateRecipe(recipe: Recipe, user: User)

//    fun DeleteRecipe(recipe: Recipe, user: User)

    fun SearchRecipe(keyword: String, user: User): List<Recipe>?

    fun GetAllRecipes(user: User): List<Recipe>?

    // Inventory service will be a facade for confirming if the userId is real
    // and store, search, modify entities for the user.

    // Move to a service called recipe service that will have custom logic for recipes
    //TODO: Function that answers if a user can prepare a recipe
    //TODO: Function that changes the stocks when a user consumes a recipe
}
