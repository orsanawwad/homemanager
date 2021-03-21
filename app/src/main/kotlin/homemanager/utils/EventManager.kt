package homemanager.utils

//typealias Observer<DATA> = (data: DATA) -> Unit;

typealias EventConsumer = (EventManager.Event<out Any>) -> Unit

class EventManager {
    enum class EventType {
        UserRegistered,
        UserLoggedIn
    }

    class Event<T>(val type: EventType, val payload: T? = null)

    private val subscriptions = mutableMapOf<EventType, List<EventConsumer>>()

    fun <T> Subscribe(type: EventType, handler: (Event<T>) -> Unit) {
        subscriptions.merge(type, listOf(handler as EventConsumer)) { existing, new ->
            existing + new
        }
    }

    fun Publish(event: Event<out Any>) = subscriptions[event.type]?.let { handlers ->
        handlers.forEach { it(event) }
    }
}