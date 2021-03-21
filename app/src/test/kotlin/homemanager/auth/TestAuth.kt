package homemanager.auth

import org.junit.jupiter.api.Test
import java.util.*

class TestAuth {

    @Test
    fun TestAuthFactory() {
        val factory = AuthFactory()

        val username = "Test1"
        val password = "Test2"
        val req = factory.CreateUsernamePasswordReq(username,password)

        assert(req.username == username)
        assert(req.password == password)
    }

    @Test
    fun TestInMemoryAuthProvider() {
        val userRepository: IUserRepository = InMemoryUserRepository()
        val loginService: IAuthService = InMemoryAuthService(userRepository)
        //TODO: Replace with factory
        var user : User? = User(
                UUID.randomUUID().toString(),
                "Test1",
                "Test2",
                "test",
                "test@example.com",
                "0552223333"
        )
        val authRequest = AuthFactory().CreateUsernamePasswordReq("test","test")
        if (user != null) {
            loginService.Register(authRequest,user)
//            userRepository.Save(user)
        } else {
            // Why...
            assert(false)
        }

        user = loginService.Login(authRequest)

        if (user != null) {
            assert(user == userRepository.GetUserByID(user.id))
            assert(user == userRepository.GetUserByEmail(user.email))
            assert(user == userRepository.GetUserByUserName(user.username))
            assert(user == userRepository.GetUserByPhone(user.phone))
        } else {
            // There's gotta be a better way
            assert(false)
        }
    }

    @Test
    fun TestInMemoryUserRepository() {
        val userRepository: IUserRepository = InMemoryUserRepository()
        //TODO: Replace with factory
        val user = User(
                UUID.randomUUID().toString(),
                "Test1",
                "Test2",
                "test",
                "test@example.com",
                "0552223333"
        )
        userRepository.Save(user)

        assert(user == userRepository.GetUserByID(user.id))
        assert(user == userRepository.GetUserByEmail(user.email))
        assert(user == userRepository.GetUserByUserName(user.username))
        assert(user == userRepository.GetUserByPhone(user.phone))
    }
}