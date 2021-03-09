package homemanager.app

import kotlin.system.exitProcess


fun main() {
    val userFactory: AbstractUserFactory = VeryGenericUserFactory(VeryGenericStorage().getUserStore())

    //Login method can be more abstracted
    val user: IUser = userFactory.Login("orsanawwad","123123") ?: exitProcess(1)

    //val recipeStore

    //val productStore

    //val

    println(user.GetFullname())


}
