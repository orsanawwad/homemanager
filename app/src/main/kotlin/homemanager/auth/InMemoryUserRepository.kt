package homemanager.auth

class InMemoryUserRepository: IUserRepository {

    private val userMap = HashMap<String, User>()

    override fun Save(user: User) {
        if (userMap[user.id] == null) {
            userMap[user.id] = user
        }
    }

    override fun GetUserByID(id: String): User? {
        return userMap[id]
    }

    override fun GetUserByUserName(userName: String): User? {
        for (user in userMap.values) {
            if (user.username == userName) return user
        }
        return null
    }

    override fun GetUserByPhone(phone: String): User? {
        for (user in userMap.values) {
            if (user.phone == phone) return user
        }
        return null
    }

    override fun GetUserByEmail(email: String): User? {
        for (user in userMap.values) {
            if (user.email == email) return user
        }
        return null
    }

}