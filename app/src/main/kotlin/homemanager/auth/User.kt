package homemanager.auth


data class User(
        val ID: String,
        val FirstName: String,
        val LastName: String,
        val UserName: String, // username
        val Email: String,
        val Phone: String
)