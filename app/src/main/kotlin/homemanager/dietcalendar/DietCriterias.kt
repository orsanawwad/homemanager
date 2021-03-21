package homemanager.dietcalendar

import homemanager.inventory.Product
import homemanager.inventory.ProductMetadata
import homemanager.inventory.Recipe
import homemanager.utils.ICriteria
import homemanager.utils.ICriteriaCustomReturn
import kotlin.random.Random

class RandomMealCriteria: ICriteriaCustomReturn<List<Recipe>, List<Recipe>> {
    override fun Search(searchList: List<List<Recipe>>): List<Recipe> {
        val mealList = mutableListOf<Recipe>()
        for(zoneRecipesList in searchList) {
            mealList.add(zoneRecipesList.random())
        }
        return mealList
    }
}

class PriceLessThanCriteria(private val price: String): ICriteria<Product> {
    override fun Search(searchList: List<Product>): List<Product> {
        val pr = price.toIntOrNull() ?: return listOf()
        return searchList.filter {
            it.metadata[ProductMetadata.PRICE]?.toIntOrNull()?: Int.MAX_VALUE < pr
        }
    }

}

class DietCriteriaFactory {
    fun RandomMeal(): ICriteriaCustomReturn<List<Recipe>, List<Recipe>> = RandomMealCriteria()
    fun PriceLessThan(price: String): ICriteria<Product> = PriceLessThanCriteria(price)
}