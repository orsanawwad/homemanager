package homemanager.inventory

import homemanager.app.AppContainer
import homemanager.auth.IAuthService
import homemanager.auth.User
import homemanager.utils.EventManager

class InMemoryInventoryService(
        private var userService: IAuthService =  AppContainer.authService,
        private var inventoryRepository: IInventoryRepository = AppContainer.inventoryRepository,
        private var inventorySearchFilterFactory: InventorySearchFactory = AppContainer.inventorySearchFilterFactory,
        private var userServiceEvents: EventManager = AppContainer.eventManager
): IInventoryService {

    init {
        this.userServiceEvents.Subscribe<User>(EventManager.EventType.UserRegistered) {
            if (it.payload != null) {
                this.inventoryRepository.InitForUserID(it.payload.id)
            }
        }
    }

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
        val searchCriteria = inventorySearchFilterFactory.SearchProductByName(keyword)
        return inventoryRepository.GetAllProducts(user.id)?.let { searchCriteria.Search(it) }
    }

    override fun GetAllProducts(user: User): List<Product>? {
        if (isNotValidUser(user)) return null
        return inventoryRepository.GetAllProducts(user.id)
    }

    override fun AddStock(stock: Stock, user: User) {
        if (isNotValidUser(user)) return
        if (inventoryRepository.GetProduct(stock.product.id,user.id) == null) return
        inventoryRepository.AddStock(stock,user.id)
    }

    override fun GetStocksForProduct(productId: String, user: User): List<Stock>? {
        if (isNotValidUser(user)) return null
        if (inventoryRepository.GetProduct(productId,user.id) == null) return null
        val searchCriteria = inventorySearchFilterFactory.ReturnStocksForProduct(productId)
        return inventoryRepository.GetAllStocks(user.id)?.let { searchCriteria.Search(it) }
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
        val searchCriteria = inventorySearchFilterFactory.SearchRecipeByName(keyword)
        return inventoryRepository.GetAllRecipe(user.id)?.let { searchCriteria.Search(it) }
    }

    override fun GetAllRecipes(user: User): List<Recipe>? {
        if (isNotValidUser(user)) return null
        return inventoryRepository.GetAllRecipe(user.id)
    }
}