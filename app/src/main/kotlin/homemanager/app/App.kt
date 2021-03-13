package homemanager.app

import homemanager.auth.AuthFactory
import homemanager.auth.AuthRequest
import homemanager.auth.IUserRepository
import homemanager.auth.InMemoryUserRepository

// Container for holding dependency instances
class AppContainer {
    companion object {
        val userRepository: IUserRepository = InMemoryUserRepository()
        val authFactory = AuthFactory()
    }
}

fun main() {
    println("Hello World")
}
