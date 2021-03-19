package homemanager.smartrecipe

import homemanager.app.AppContainer
import homemanager.auth.IAuthService
import homemanager.auth.User
import homemanager.inventory.IInventoryService
import homemanager.inventory.Recipe

class SmartRecipeService(
        private var userService: IAuthService = AppContainer.authService,
        private var inventoryService: IInventoryService = AppContainer.inventoryService
): ISmartRecipeService {

    override fun CanPrepareRecipe(recipe: Recipe, user: User): Boolean {
        if(!userService.ValidateUser(user)) return false
        for ((product, quantity) in recipe.ingredients) {
            val stocksList = inventoryService.GetStocksForProduct(product.id,user) ?: return false
            var found = false
            // TODO: check expire date
            for (stock in stocksList) {
                if (quantity > stock.quantity) {
                    found = true
                    break
                }
            }
            if (!found) return false
        }
        return true
    }

    //TODO: Needs heavy testing
    override fun ConsumeRecipe(recipe: Recipe, user: User): Boolean {
        if(!userService.ValidateUser(user)) return false

        for ((product, quantity) in recipe.ingredients) {
            val stocksList = inventoryService.GetStocksForProduct(product.id,user) ?: return false
            val stocksSortedList = stocksList.sortedBy { it.expireDate }
//            val stocksSortedList = stocksList.sortedByDescending { it.expireDate }
            var found = false
            // TODO: check expire date
            for (stock in stocksSortedList) {
                if (quantity > stock.quantity) {
                    stock.quantity -= quantity
                    inventoryService.UpdateStock(stock, user)
                    found = true
                    break
                }
            }
            if (!found) return false
        }
        return true
    }
}