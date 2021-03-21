package homemanager.auth

data class AuthRequest(
        val username: String? = null,
        val password: String? = null,
        val idToken: String? = null,
        val accessToken: String? = null
)
