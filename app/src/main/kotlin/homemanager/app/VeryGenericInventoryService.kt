package homemanager.app

import java.util.*
import kotlin.collections.HashMap

// TODO: Add to consructor some generic storage driver
// TODO: Add Inventory factory
class VeryGenericInventoryService(private val inventoryFactory: AbstractInventoryFactory): IInventoryService {

    // user to ids, used for authorization
    private val userInventories = HashMap<IUser, HashSet<String>>()

    // id to inventory
    private val inventories = HashMap<String, IInventory>()

    override fun CreateInventory(user: IUser): IInventory {
        // Needs a factory to create inventories
        // Inventories will be stored here for now, later on it should be moved to a storage layer
//        TODO("Not yet implemented")
        if (userInventories[user] == null) {
            userInventories[user] = HashSet()
        }

        val inventory = inventoryFactory.CreateInventory()
        // if it fails here, something is really messed up
        userInventories[user]!!.add(inventory.GetID())
        inventories[inventory.GetID()] = inventory

        return inventory

//        if (userInventories[user] != null) {
//
//        } else {
//            println("What")
//        }

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