package homemanager.app

class VeryGenericUser(
        private val firstname: String,
        private val lastname: String,
        private val username: String
): IUser {
    private var recipeService: IRecipeService? = null

    override fun GetFirstname(): String {
        return firstname
    }

    override fun GetLastname(): String {
        return lastname
    }

    override fun GetFullname(): String {
        return "$firstname $lastname"
    }

    override fun GetUsername(): String {
        return username
    }

}