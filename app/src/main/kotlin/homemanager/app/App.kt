package homemanager.app

import homemanager.auth.*
import homemanager.inventory.IInventoryRepository
import homemanager.inventory.IInventoryService
import homemanager.inventory.InMemoryInventoryRepository
import homemanager.inventory.InMemoryInventoryService

// Container for holding dependency instances
class AppContainer {
    companion object {
        val userRepository: IUserRepository = InMemoryUserRepository()
        val authService: IAuthService = InMemoryAuthService()
        val inventoryRepository: IInventoryRepository = InMemoryInventoryRepository()
        val inventoryService: IInventoryService = InMemoryInventoryService()
        val authFactory = AuthFactory()
    }
}

fun main() {
    println("Hello World")
}
