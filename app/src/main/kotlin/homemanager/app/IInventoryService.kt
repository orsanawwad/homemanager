package homemanager.app


interface IInventoryService {
    // create new inventory for user
    fun CreateInventory(user: IUser): IInventory
    // return all inventories for user
    fun GetAllInventories(user: IUser): List<IInventory>?
    // id for specific inventory, user for authentication purposes
    fun GetInventory(id: String, user: IUser): IInventory?
}