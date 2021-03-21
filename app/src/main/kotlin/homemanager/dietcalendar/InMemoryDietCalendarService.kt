package homemanager.dietcalendar

import homemanager.app.AppContainer
import homemanager.auth.IAuthService
import homemanager.auth.User
import homemanager.inventory.*
import homemanager.utils.EventManager
import homemanager.utils.ICriteriaCustomReturn
import java.time.LocalDateTime


class InMemoryDietCalendarService(
        private var userServiceEvents: EventManager = AppContainer.eventManager,
        private val inventoryService : IInventoryService = AppContainer.inventoryService,
        private val dietCalendarRepo : IDietCalendarRepository = AppContainer.dietCalendarRepository,
        private val dietCriteriaFactory: DietCriteriaFactory = AppContainer.dietCriteriaFactory,
        private val authService: IAuthService = AppContainer.authService
): IDietCalendarService {

    init {
        this.userServiceEvents.Subscribe<User>(EventManager.EventType.UserRegistered) {
            if (it.payload != null) {
                this.dietCalendarRepo.initForUser(it.payload.id)
            }
        }
    }


    private fun isValidUser(user: User): Boolean {
        return authService.ValidateUser(user)
    }

    override fun createUserMealCalendar(mealCalendar: DietCalendar, user : User) : DietCalendar? {
        if(!isValidUser(user)) return null
        if(dietCalendarRepo.createUserMealCalendar(user.id, mealCalendar)) {
            return mealCalendar
        }
        return null
    }

    override fun returnUserMealCalendars(user: User) : List<DietCalendar>? {
        if(!isValidUser(user)) return null
        return dietCalendarRepo.getAllCalendars(user.id)
    }

    override fun numberOfUserMealCalendars(user: User): Int {
        if(!isValidUser(user)) return -1
        return returnUserMealCalendars(user)?.size ?: -1
    }

    override fun deleteUserMealCalendar(calendar: DietCalendar, user: User) : Boolean {
        return dietCalendarRepo.deleteUserCalendar(user.id, calendar.id)
    }

    override fun addRecipeForDayZone(calendar: DietCalendar, dayZone: DayZone, recipe: Recipe, user: User): Boolean {
        dayZone.recipes.add(recipe.id)
//        if (calendar.dayZones[dayZone.index].index != dayZone.index) return false
        if (!calendar.hasDayZone(dayZone)) return false
        calendar.dayZones[dayZone.index] = dayZone
        dietCalendarRepo.updateUserCalendar(userID = user.id,calendar = calendar)
        return true
    }

    override fun deleteRecipeForDayZone(calendar: DietCalendar, dayZone: DayZone, recipe: Recipe, user: User): Boolean {
        dayZone.recipes.removeIf { it == recipe.id }
//        if (calendar.dayZones[dayZone.index].index != dayZone.index) return false
        if (!calendar.hasDayZone(dayZone)) return false
        calendar.dayZones[dayZone.index] = dayZone
        dietCalendarRepo.updateUserCalendar(userID = user.id,calendar = calendar)
        return true
    }

    override fun getCurrentMeals(calendar: DietCalendar, currentTime: LocalDateTime, user: User): List<Recipe>? {
        val recipesIDs = calendar.getCurrentDayZone(currentTime)?.recipes ?: return null
        val userRecipes = inventoryService.GetAllRecipes(user)
        return userRecipes?.filter { recipesIDs.contains(it.id) }
    }

    override fun getMealsForToday(calendar: DietCalendar, currentTime: LocalDateTime, applyRestriction: Boolean, user: User): List<List<Recipe>>? {
        val listOfRecipeRanges: MutableList<MutableList<Recipe>> = ArrayList()
        val userRecipes = inventoryService.GetAllRecipes(user) ?: return null
        for (dayZone in calendar.dayZones) {
            val recipes = userRecipes.filter { dayZone.recipes.contains(it.id) }
            listOfRecipeRanges.add(recipes.toMutableList())
        }
        if (applyRestriction) {
            val filteredRecipeRanges: MutableList<MutableList<Recipe>> = ArrayList()
            for (listOfRecipeRange in listOfRecipeRanges) {
                val filteredList = calendar.dietRestrictions.Search(listOfRecipeRange.toList())
                filteredRecipeRanges.add(filteredList.toMutableList())
            }
            return filteredRecipeRanges
        }
        return listOfRecipeRanges
    }

    override fun getRandomMealsForToday(calendar: DietCalendar, currentTime: LocalDateTime, user: User): List<Recipe>? {
        val meals = getMealsForToday(calendar, currentTime, false, user)
        val randomMeal: ICriteriaCustomReturn<List<Recipe>,List<Recipe>> = dietCriteriaFactory.RandomMeal()
        return meals?.let { randomMeal.Search(it) }
    }

}