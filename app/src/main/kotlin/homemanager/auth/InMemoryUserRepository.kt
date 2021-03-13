package homemanager.auth

class InMemoryUserRepository: IUserRepository {

    private val userMap = HashMap<String, User>()

    override fun Save(user: User) {
        if (userMap[user.ID] == null) {
            userMap[user.ID] = user
        }
    }

    override fun GetUserByID(id: String): User? {
        return userMap[id]
    }

    override fun GetUserByUserName(userName: String): User? {
        for (user in userMap.values) {
            if (user.UserName == userName) return user
        }
        return null
    }

    override fun GetUserByPhone(phone: String): User? {
        for (user in userMap.values) {
            if (user.Phone == phone) return user
        }
        return null
    }

    override fun GetUserByEmail(email: String): User? {
        for (user in userMap.values) {
            if (user.Email == email) return user
        }
        return null
    }

}