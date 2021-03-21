package homemanager.dietcalendar

import homemanager.inventory.Recipe
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

/*
    DietCalendar:
        UserID: string
        Restrictions:
            - Restriction1
            - Restriction2
        DayZones:
            - Zone1
                ID: string or Int (by index)
                Start: Long or Int
                End: Long or Int
                Recipes: Array<List>
            - Zone2
            - Zone3
            - ...
 */


data class DietCalendar(
        val id : String = UUID.randomUUID().toString(),
        val dayZones: MutableList<DayZone> = mutableListOf(),
        val dietRestrictions: DietRestrictions
) {

    fun hasDayZone(dayZone: DayZone) = dayZones[dayZone.index].index == dayZone.index


    fun getCurrentDayZone(currentTime : LocalDateTime) : DayZone? {
        val currentHour = currentTime.hour
        for(dayZone in dayZones) {
            if(currentHour >= dayZone.start && currentHour < dayZone.end) {
                return dayZone
            }
        }
        return null
    }
}