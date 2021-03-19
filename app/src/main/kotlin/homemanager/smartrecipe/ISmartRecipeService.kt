package homemanager.smartrecipe

import homemanager.auth.User
import homemanager.inventory.Recipe

interface ISmartRecipeService {
    fun CanPrepareRecipe(recipe: Recipe, user: User): Boolean
    fun ConsumeRecipe(recipe: Recipe, user: User): Boolean
}