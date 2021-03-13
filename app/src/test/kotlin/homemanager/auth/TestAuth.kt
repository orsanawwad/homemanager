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
        val loginProvider: IAuthProvider = InMemoryAuthProvider(userRepository)
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
            loginProvider.Register(authRequest,user)
//            userRepository.Save(user)
        } else {
            // Why...
            assert(false)
        }

        user = loginProvider.Login(authRequest)

        if (user != null) {
            assert(user == userRepository.GetUserByID(user.ID))
            assert(user == userRepository.GetUserByEmail(user.Email))
            assert(user == userRepository.GetUserByUserName(user.UserName))
            assert(user == userRepository.GetUserByPhone(user.Phone))
        } else {
            // There's gotta be a better way
            assert(false)
        }
    }

    @Test
    fun TestInMemoryUserRepository() {
        val userRepository: IUserRepository = InMemoryUserRepository()
        val user = User(
                UUID.randomUUID().toString(),
                "Test1",
                "Test2",
                "test",
                "test@example.com",
                "0552223333"
        )
        userRepository.Save(user)

        assert(user == userRepository.GetUserByID(user.ID))
        assert(user == userRepository.GetUserByEmail(user.Email))
        assert(user == userRepository.GetUserByUserName(user.UserName))
        assert(user == userRepository.GetUserByPhone(user.Phone))
    }
}