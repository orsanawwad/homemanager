package homemanager.inventory

import java.util.*

class Recipe private constructor(
        val id: String = null ?: UUID.randomUUID().toString(),
        var name: String = "",
        var description: String = "",
        val categories: MutableList<String> = null ?: LinkedList(),
        var preparation: String = "",
        val ingredients: MutableList<Pair<Product,Int>> = LinkedList()
) {

    // Builder Pattern for Recipe
    // TODO: Refactor, constructor is optional and only used to load back data
    data class RecipeBuilder(
            val id: String = null ?: UUID.randomUUID().toString(),
            var name: String = "",
            var description: String = "",
            val categories: MutableList<String> = null ?: LinkedList(),
            var preparation: String = "",
            val ingredients: MutableList<Pair<Product,Int>> = LinkedList()
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