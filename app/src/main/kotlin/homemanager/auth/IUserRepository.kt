package homemanager.auth

interface IUserRepository {
    fun Save(user: User)
    fun GetUserByID(id: String): User?
    fun GetUserByUserName(userName: String): User?
    fun GetUserByPhone(phone: String): User?
    fun GetUserByEmail(email: String): User?
}