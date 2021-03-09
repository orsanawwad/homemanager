package homemanager.app

import java.util.*
import kotlin.collections.HashMap

// TODO: Add to consructor some generic storage driver
// TODO: Add Inventory factory
class VeryGenericInventoryService: IInventoryService {

    // user to ids, used for authorization
    val userInventories = HashMap<IUser, Set<String>>()

    // id to inventory
    val inventories = HashMap<String, IInventory>()

    override fun CreateInventory(user: IUser): IInventory {
        // Needs a factory to create inventories
        // Inventories will be stored here for now, later on it should be moved to a storage layer
        TODO("Not yet implemented")
    }

    override fun GetAllInventories(user: IUser): List<IInventory>? {
        val inventoryIds = userInventories[user] ?: return null
        val results = LinkedList<IInventory>()
        for (id in inventoryIds) {
            val inv = inventories[id]
            if (inv != null) {
                results.add(inv)
            } else {
                print("Error: could not find inventory $id for user ${user.GetUsername()}")
            }
        }
        return results
    }

    override fun GetInventory(id: String, user: IUser): IInventory? {
        val inventoryId = userInventories[user]?.contains(id) ?: return null
        if (inventoryId) {
            return inventories[id]
        }
        return null
    }
}