package homemanager.app

// Very barebone example
class VeryGenericUserFactory(private val userStore: HashMap<Pair<String,String>, IUser>): AbstractUserFactory() {

    override fun Login(username: String, password: String): IUser? {
        // indexing operator
        return userStore[Pair<String,String>(username,password)]
    }

}