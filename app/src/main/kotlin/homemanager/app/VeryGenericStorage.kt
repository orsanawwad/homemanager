package homemanager.app

class VeryGenericStorage {
    fun getUserStore(): HashMap<Pair<String,String>, IUser> {
        val store = HashMap<Pair<String,String>, IUser>()

        store[Pair<String,String>("orsanawwad","123123")] = VeryGenericUser(
                firstname = "Orsan",
                lastname = "Awwad",
                username = "orsanawwad"
        );

        return store
    }
}