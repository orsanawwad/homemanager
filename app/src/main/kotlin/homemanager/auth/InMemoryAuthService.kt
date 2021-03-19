package homemanager.auth

import homemanager.app.AppContainer
import homemanager.utils.EventManager
import kotlin.collections.HashMap

class InMemoryAuthService(
        private val userRepository: IUserRepository = AppContainer.userRepository,
        private val eventManager: EventManager = AppContainer.eventManager
): IAuthService {

    private var storedAuth = HashMap<Pair<String, String>, String>()

    override fun Register(authRequest: AuthRequest, user: User): Boolean {
        if (authRequest.username != null && authRequest.password != null) {
            storedAuth[Pair(authRequest.username,authRequest.password)] = user.id
            userRepository.Save(user)
            eventManager.Publish(EventManager.Event(EventManager.EventType.UserRegistered,user))
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