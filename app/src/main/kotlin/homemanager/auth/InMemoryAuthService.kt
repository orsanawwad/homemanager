package homemanager.auth

import homemanager.app.AppContainer

class InMemoryAuthService(userRepository: IUserRepository? = null): IAuthService {

    private var userRepository: IUserRepository = userRepository ?: AppContainer.userRepository

    private var storedAuth = HashMap<Pair<String, String>, String>()

    override fun Register(authRequest: AuthRequest, user: User): Boolean {
        if (authRequest.username != null && authRequest.password != null) {
            storedAuth[Pair(authRequest.username,authRequest.password)] = user.id
            userRepository.Save(user)
            return true
        }
        return false
    }

    override fun Login(authRequest: AuthRequest): User? {
        if (authRequest.username != null && authRequest.password != null) {
            val id = storedAuth[Pair(authRequest.username,authRequest.password)]
            return id?.let { userRepository.GetUserByID(it) }
        }
        return null
    }

    override fun ValidateUser(user: User): Boolean {
        return userRepository.GetUserByID(user.id) != null
    }
}