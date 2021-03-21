package homemanager.auth


data class User(
        val id: String,
        val firstname: String,
        val lastname: String,
        val username: String, // username
        val email: String,
        val phone: String
)