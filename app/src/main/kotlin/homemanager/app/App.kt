package homemanager.app

import homemanager.auth.*
import homemanager.inventory.IInventoryRepository
import homemanager.inventory.IInventoryService
import homemanager.inventory.InMemoryInventoryRepository
import homemanager.inventory.InMemoryInventoryService

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
        val inventoryRepository: IInventoryRepository = InMemoryInventoryRepository()
        val inventoryService: IInventoryService = InMemoryInventoryService()
        val authFactory = AuthFactory()
    }
}

fun main() {
    println("Hello World")
}
