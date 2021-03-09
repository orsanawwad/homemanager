package homemanager.app

interface IUser {
    fun GetFirstname(): String
    fun GetLastname(): String
    fun GetFullname(): String
    fun GetUsername(): String
//    fun Get
}
// Inject IUser in order to receive related items