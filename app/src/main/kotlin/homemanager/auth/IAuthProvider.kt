package homemanager.auth

interface IAuthProvider {
    fun Register(authRequest: AuthRequest, user: User): Boolean
    fun Login(authRequest: AuthRequest): User?
}