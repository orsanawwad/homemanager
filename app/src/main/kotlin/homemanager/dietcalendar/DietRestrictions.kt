package homemanager.dietcalendar

import homemanager.inventory.Recipe
import homemanager.utils.ICriteria

class DietRestrictions private constructor(
        val restrictions: MutableList<ICriteria<Recipe>>
): ICriteria<Recipe> {
    override fun Search(searchList: List<Recipe>): List<Recipe> {
        var result = searchList.toMutableList()
        for (restriction in restrictions) {
            result = restriction.Search(result.toList()).toMutableList()
        }
        return result
    }

    data class DietRestrictionsBuilder(
            val restrictions: MutableList<ICriteria<Recipe>> = mutableListOf()
    ) {
        fun AddRestriction(criteria: ICriteria<Recipe>) = apply { restrictions.add(criteria) }
        fun Build() = DietRestrictions(restrictions)
    }

}