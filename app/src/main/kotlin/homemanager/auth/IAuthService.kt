package homemanager.auth

interface IAuthService {
    fun Register(authRequest: AuthRequest, user: User): Boolean
    fun Login(authRequest: AuthRequest): User?
    fun ValidateUser(user: User): Boolean
}