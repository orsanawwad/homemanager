package homemanager.app

import java.util.*
import kotlin.system.exitProcess


fun main() {
    val userFactory: AbstractUserFactory = VeryGenericUserFactory(VeryGenericStorage().getUserStore())

    //Login method can be more abstracted
    val user: IUser = userFactory.Login("orsanawwad","123123") ?: exitProcess(1)

    val client = Client()

    // Make it more dependency injectable?
    val inventoryService: IInventoryService = VeryGenericInventoryService(VeryGenericInventoryFactory())

    val productFactory: AbstractProductFactory = VeryGenericProductFactory()

    val stockFactory: AbstractStockFactory = VeryGenericStockFactory()

    client.SetInventoryService(inventoryService)

    client.SetCurrentUser(user)

    client.SetCurrentProductFactory(productFactory)

    client.SetStockFactory(stockFactory)

    println(user.GetFullname())

    client.InvokeStuff()

//    val uuid = UUID.randomUUID().toString()
//    println(uuid)


}
