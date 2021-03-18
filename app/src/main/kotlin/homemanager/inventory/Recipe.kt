package homemanager.inventory

import java.util.*
import kotlin.collections.ArrayList

class Recipe private constructor(
        val id: String = null ?: UUID.randomUUID().toString(),
        var name: String = "",
        var description: String = "",
        val categories: MutableList<String> = null ?: ArrayList(),
        var preparation: String = "",
        val ingredients: MutableList<Pair<Product,Int>> = ArrayList()
) {

    // Builder Pattern for Recipe
    // TODO: Refactor, constructor is optional and only used to load back data
    data class RecipeBuilder(
            val id: String = null ?: UUID.randomUUID().toString(),
            var name: String = "",
            var description: String = "",
            val categories: MutableList<String> = null ?: ArrayList(),
            var preparation: String = "",
            val ingredients: MutableList<Pair<Product,Int>> = ArrayList()
    ) {

        //TODO: check if products exist?
        fun Name(name: String) = apply { this.name = name }
        fun Description(description: String) = apply { this.description=description }
        fun AddCategory(category: String) = apply { this.categories.add(category) }
        fun Preparation(preparation: String) = apply { this.preparation = preparation }
        fun AddIngredient(product: Product, quantity: Int) = apply { this.ingredients.add(Pair(product,quantity)) }
        fun Build() = Recipe(id, name, description, categories, preparation, ingredients)
    }

}