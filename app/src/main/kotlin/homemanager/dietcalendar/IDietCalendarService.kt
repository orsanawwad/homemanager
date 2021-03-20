package homemanager.dietcalendar

import homemanager.auth.User
import homemanager.inventory.Recipe
import java.time.LocalDateTime


interface IDietCalendarService {
    fun createUserMealCalendar(mealCalendar: DietCalendar, user: User) : DietCalendar?
    fun returnUserMealCalendars(user: User) : List<DietCalendar>?
    fun numberOfUserMealCalendars(user: User) : Int
    fun deleteUserMealCalendar(calendar: DietCalendar, user : User): Boolean

    fun addRecipeForDayZone(calendar: DietCalendar, dayZone: DayZone, recipe: Recipe, user: User): Boolean
    fun deleteRecipeForDayZone(calendar: DietCalendar, dayZone: DayZone, recipe: Recipe, user: User): Boolean
    fun getCurrentMeals(calendar: DietCalendar, currentTime: LocalDateTime, user: User): List<Recipe>?
    fun getMealsForToday(calendar: DietCalendar, currentTime: LocalDateTime, applyRestriction: Boolean, user: User): List<List<Recipe>>?
    fun getRandomMealsForToday(calendar: DietCalendar, currentTime: LocalDateTime, user: User): List<Recipe>?

}