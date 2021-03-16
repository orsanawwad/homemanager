package homemanager.inventory

import homemanager.app.AppContainer
import homemanager.auth.IUserRepository

class InMemoryInventoryService(
        userRepository: IUserRepository? = null,
        inventoryRepository: IInventoryRepository? = null
): IInventoryService {
    private var userRepository: IUserRepository = userRepository ?: AppContainer.userRepository
    private var inventoryRepository: IInventoryRepository = inventoryRepository ?: AppContainer.inventoryRepository



}