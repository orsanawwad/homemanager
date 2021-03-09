package homemanager.app

interface IRecipe {
    fun GetName(): String
    fun GetServings(): Int //Should be more dynamic
    fun GetPreparationSteps(): String //Should be just a markdown text on how to prepare such recipe
    fun GetIngredients(): List<IIngredient>
}