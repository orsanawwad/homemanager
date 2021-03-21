package homemanager.dietcalendar

data class DayZone(
        val index: Int = -1,
        val start: Int = -1,
        val end: Int = -1,
        val recipes: MutableList<String> = mutableListOf()
)