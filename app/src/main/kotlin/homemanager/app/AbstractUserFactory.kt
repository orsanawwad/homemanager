package homemanager.app

abstract class AbstractUserFactory {
    abstract fun Login(username: String, password: String): IUser?
}