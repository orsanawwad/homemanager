package homemanager.dietcalendar

import homemanager.inventory.Recipe
import homemanager.utils.ICriteria

class DietCalendarBuilder {

    private var timeZoneID : Int = 0
    private val mealTimesArray : MutableList<Pair<Int, Pair<Int, Int>>> = ArrayList()
    private val recipes: MutableList<MutableList<String>> = ArrayList()
    private var restrictionBuilder = DietRestrictions.DietRestrictionsBuilder()

    fun addNewDayZone(timeFrame : Pair<Int, Int>, recipeIdsArray: MutableList<String> = ArrayList()) : DietCalendarBuilder? {
        if(checkTimeFrameValidity(timeFrame)) {
            mealTimesArray.add(Pair(timeZoneID, timeFrame))
            recipes.add(recipeIdsArray)
            timeZoneID++
            return this
        }
        return null
    }

    fun addRestriction(criteria: ICriteria<Recipe>) : DietCalendarBuilder {
        restrictionBuilder = restrictionBuilder.AddRestriction(criteria)
        return this
    }

    private fun checkTimeFrameValidity(newFrame: Pair<Int, Int>) : Boolean {
        var frame : Pair<Int, Int>
        if(newFrame.first > newFrame.second || newFrame.first < 0 || newFrame.first > 24
                || newFrame.second < 0 || newFrame.second > 24) return false

        for(framePair in mealTimesArray) {
            frame = framePair.second
            if((newFrame.first < frame.second && newFrame.second > frame.second) ||
                    (newFrame.second > frame.first && newFrame.first < frame.first)) {
                return false
            }
        }
        return true
    }

    private fun build(): DietCalendar {
        val dayZones: MutableList<DayZone> = mutableListOf()
        for ((index, mealTime) in mealTimesArray.withIndex()) {
            dayZones.add(DayZone(mealTime.first,mealTime.second.first,mealTime.second.second, recipes[index]))
        }
        return DietCalendar(dayZones = dayZones, dietRestrictions = this.restrictionBuilder.Build())
    }

}