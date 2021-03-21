package homemanager.app

import homemanager.app.handlers.UserServiceHandler
import homemanager.auth.*
import homemanager.dietcalendar.*
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
//     - Calendar service
//     - Logger service?
//     - Notification service?

//TODO: Add logger
//TODO: Rename functions to camelcase
// Container for holding dependency instances
class AppContainer {
    companion object {
        val eventManager = EventManager()
        val userRepository: IUserRepository = InMemoryUserRepository()
        private val inMemoryUserService = InMemoryAuthService()
        val authService: IAuthService = inMemoryUserService
        private val inMemoryInventory = InMemoryInventoryRepository()
        val inventoryRepository: IInventoryRepository = inMemoryInventory
//        val inventorySearch: IInventorySearchFilter = inMemoryInventory
        val inventorySearchFilterFactory = InventorySearchFactory()
        val inventoryService: IInventoryService = InMemoryInventoryService()
        val dietCalendarRepository : IDietCalendarRepository = InMemoryDietCalendarRepository()
        val dietCalendarService: IDietCalendarService = InMemoryDietCalendarService()
        val dietCriteriaFactory: DietCriteriaFactory = DietCriteriaFactory()
        val authFactory = AuthFactory()
    }
}

fun main() {
    println("Hello World")
}
