package homemanager.inventory

import homemanager.app.AppContainer
import homemanager.auth.IAuthService
import homemanager.auth.User

class InMemoryInventoryService(
        authService: IAuthService? = null,
        inventoryRepository: IInventoryRepository? = null,
        inventorySearch: IInventorySearchStrategy? = null,
        inventorySearchStrategyFactory: SearchStrategyFactory? = null
): IInventoryService {
//    private var userRepository: IUserRepository = userRepository ?: AppContainer.userRepository
    private var userService: IAuthService = authService ?: AppContainer.authService
    private var inventoryRepository: IInventoryRepository = inventoryRepository ?: AppContainer.inventoryRepository
    private var inventorySearch: IInventorySearchStrategy = inventorySearch ?: AppContainer.inventorySearch
    private var inventorySearchStrategyFactory: SearchStrategyFactory = inventorySearchStrategyFactory ?: AppContainer.inventorySearchStrategyFactory


    private fun isValidUser(user: User): Boolean {
        return userService.ValidateUser(user)
    }

    private fun isNotValidUser(user: User): Boolean {
        return !isValidUser(user)
    }

    override fun AddProduct(product: Product, user: User) {
        if (isNotValidUser(user)) return
        inventoryRepository.AddProduct(product,userId = user.id)
    }

    override fun SearchProduct(keyword: String, user: User): List<Product>? {
        if (isNotValidUser(user)) return null
        return inventorySearch.SearchProductStrategy(user.id, inventorySearchStrategyFactory.SearchProductByName(keyword))
    }

    override fun GetAllProducts(user: User): List<Product>? {
        if (isNotValidUser(user)) return null
        return inventorySearch.SearchProductStrategy(user.id, inventorySearchStrategyFactory.ReturnAllPrducts())
    }

    override fun AddStock(stock: Stock, user: User) {
        if (isNotValidUser(user)) return
        if (inventoryRepository.GetProduct(stock.product.id,user.id) == null) return
        inventoryRepository.AddStock(stock,user.id)
    }

    override fun GetStocksForProduct(productId: String, user: User): List<Stock>? {
        if (isNotValidUser(user)) return null
        if (inventoryRepository.GetProduct(productId,user.id) == null) return null
        return inventorySearch.SearchStockStrategy(user.id, inventorySearchStrategyFactory.ReturnStocksForProduct(productId))
    }

    override fun UpdateStock(stock: Stock, user: User) {
        if (isNotValidUser(user)) return
        if (inventoryRepository.GetProduct(stock.product.id,user.id) == null) return
        val currentStock = inventoryRepository.GetStock(stock.id, user.id) ?: return
        if (stock.quantity > currentStock.quantity) return
        if (stock.quantity < 0) return
        if (stock.expireDate != currentStock.expireDate) return
        inventoryRepository.UpdateStock(stock, user.id)
    }

    override fun AddRecipe(recipe: Recipe, user: User) {
        if (isNotValidUser(user)) return
        for ((prod, quan) in recipe.ingredients) {
            if (inventoryRepository.GetProduct(prod.id, user.id) == null) return
            if (quan < 1) return
        }
        inventoryRepository.AddRecipe(recipe, user.id)
    }

    override fun UpdateRecipe(recipe: Recipe, user: User) {
        if (isNotValidUser(user)) return
        if (inventoryRepository.GetRecipe(recipe.id, user.id) == null) return
        for ((prod, quan) in recipe.ingredients) {
            if (inventoryRepository.GetProduct(prod.id, user.id) == null) return
            if (quan < 1) return
        }
        inventoryRepository.UpdateRecipe(recipe, user.id)
    }

    override fun SearchRecipe(keyword: String, user: User): List<Recipe>? {
        if (isNotValidUser(user)) return null
        return inventorySearch.SearchRecipeStrategy(user.id, inventorySearchStrategyFactory.ReturnRecipeByName(keyword))
    }

    override fun GetAllRecipes(user: User): List<Recipe>? {
        if (isNotValidUser(user)) return null
        return inventorySearch.SearchRecipeStrategy(user.id, inventorySearchStrategyFactory.ReturnAllRecipes())
    }
}