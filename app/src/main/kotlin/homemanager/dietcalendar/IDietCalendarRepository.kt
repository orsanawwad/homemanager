package homemanager.dietcalendar

interface IDietCalendarRepository {

    fun initForUser(userID: String)

    fun createUserMealCalendar(userID : String, dietCalendar : DietCalendar): Boolean
    fun getCalendar(userID: String, calendarID: String): DietCalendar?
    fun getAllCalendars(userID : String) : List<DietCalendar>?
    fun deleteUserCalendar(userID : String, calendarID : String): Boolean
    fun updateUserCalendar(userID: String, calendar: DietCalendar): Boolean
}