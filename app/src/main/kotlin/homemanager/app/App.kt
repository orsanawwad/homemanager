package homemanager.app

import homemanager.app.handlers.UserServiceHandler
import homemanager.auth.*
import homemanager.inventory.*
import homemanager.utils.EventManager

// Patterns implemented:
// - Filter Pattern
// - Dependency Injection
// - Repository Pattern
// - Factory Pattern (For constructing entities and strategies)
// - Builder Pattern (For constructing recipe)
// - Observer/PubSub pattern
// - Facade
// Removed:
// - Composition
// - Strategy Pattern
// Architect patterns:
// - Microservices:
//     - Auth/User service
//     - Inventory service
//     - Notification service?
//     - Calendar service

//TODO: Add logger
//TODO: Rename functions to camelcase
// Container for holding dependency instances
class AppContainer {
    companion object {
        val eventManager = EventManager()
        val userRepository: IUserRepository = InMemoryUserRepository()
        private val inMemoryUserService = InMemoryAuthService()
        val authService: IAuthService = inMemoryUserService
//        val authServiceEvents: IEventSource<AuthServiceEvents,User> = inMemoryUserService
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
