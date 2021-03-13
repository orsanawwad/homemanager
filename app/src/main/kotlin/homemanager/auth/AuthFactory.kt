package homemanager.auth

class AuthFactory {
    fun CreateUsernamePasswordReq(username: String, password: String): AuthRequest {
        return AuthRequest(
                username = username,
                password = password
        )
    }
}