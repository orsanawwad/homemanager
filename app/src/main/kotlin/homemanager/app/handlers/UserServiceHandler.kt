package homemanager.app.handlers

import com.beust.klaxon.Klaxon
import homemanager.app.AppContainer
import homemanager.auth.AuthRequest
import homemanager.auth.IAuthService
import homemanager.auth.User
import io.javalin.apibuilder.CrudHandler
import io.javalin.http.Context

class UserServiceHandler: CrudHandler {

    val userService: IAuthService = AppContainer.authService

    override fun create(ctx: Context) {
        val authRequest = Klaxon().parse<AuthRequest>(ctx.body())
        val user = Klaxon().parse<User>(ctx.body())
        userService.Register(authRequest!!, user!!)
//        ctx.result(user.toString())
        ctx.result(Klaxon().toJsonString(user))
    }

    override fun delete(ctx: Context, resourceId: String) {
        ctx.result("Delete $resourceId")
    }

    override fun getAll(ctx: Context) {
        val authRequest = Klaxon().parse<AuthRequest>(ctx.body())
        var user = userService.Login(authRequest!!)
        ctx.result(Klaxon().toJsonString(user))
//        ctx.result(user!!.toString())
    }

    override fun getOne(ctx: Context, resourceId: String) {
        ctx.result("Get One $resourceId")
    }

    override fun update(ctx: Context, resourceId: String) {
        ctx.result("Update $resourceId")
    }

}