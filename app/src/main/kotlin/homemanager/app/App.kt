package homemanager.app

import homemanager.auth.*
import homemanager.inventory.*

// Patterns implemented:
// - Strategy Pattern
// - Dependency Injection
// - Repository Pattern
// - Factory Pattern (For constructing entities and strategies)
// - Builder Pattern (For constructing recipe)
// - Composition
// - TODO: Observer/PubSub pattern
// Architect patterns:
// - Microservices:
//     - Auth/User service
//     - Inventory service
//     - Notification service?
//     - Calendar service

// Container for holding dependency instances
class AppContainer {
    companion object {
        val userRepository: IUserRepository = InMemoryUserRepository()
        val authService: IAuthService = InMemoryAuthService()
        private val inMemoryInventory = InMemoryInventoryRepository()
        val inventoryRepository: IInventoryRepository = inMemoryInventory
        val inventorySearch: IInventorySearchStrategy = inMemoryInventory
        val inventorySearchStrategyFactory = SearchStrategyFactory()
        val inventoryService: IInventoryService = InMemoryInventoryService()
        val authFactory = AuthFactory()
    }
}

fun main() {
    println("Hello World")
}
