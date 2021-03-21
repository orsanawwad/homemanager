package homemanager.dietcalendar


class InMemoryDietCalendarRepository : IDietCalendarRepository {


    private var usersCalendarData: HashMap<String, ArrayList<DietCalendar>> = HashMap()

    private fun createEntryIfNotExists(userID: String) {
        if (!usersCalendarData.containsKey(userID)) usersCalendarData[userID] = ArrayList()
    }

    override fun initForUser(userID: String) {
        createEntryIfNotExists(userID)
    }

    override fun createUserMealCalendar(userID: String, dietCalendar: DietCalendar): Boolean {
        if (usersCalendarData[userID]?.find { it.id == dietCalendar.id } != null) return false
        usersCalendarData[userID]?.add(dietCalendar)
        return true
    }

    override fun getCalendar(userID: String, calendarID: String): DietCalendar? {
        return usersCalendarData[userID]?.find { it.id == calendarID }
    }

    override fun getAllCalendars(userID: String): List<DietCalendar>? {
        return usersCalendarData[userID]
    }

    override fun deleteUserCalendar(userID: String, calendarID: String): Boolean {
        val calendar = usersCalendarData[userID]?.find { it.id == calendarID }
        if (calendar != null) {
            usersCalendarData[userID]?.remove(calendar)
            return true
        }
        return false
    }

    override fun updateUserCalendar(userID: String, calendar: DietCalendar): Boolean {
        if (deleteUserCalendar(userID, calendar.id)) {
            return createUserMealCalendar(userID, calendar)
        }
        return false
    }

}