package at.hannibal2.skyhanni.api.event

/**
 * Use @[HandleEvent]
 */
abstract class SkyHanniEvent protected constructor() {

    // TODO: This should only be accessible in the cancellable interface
    var isCancelled: Boolean = false
        private set

    fun post() = SkyHanniEvents.getEventHandler(javaClass).post(this)

    fun post(onError: (Throwable) -> Unit = {}) = SkyHanniEvents.getEventHandler(javaClass).post(this, onError)

    interface Cancellable {

        fun cancel() {
            val event = this as SkyHanniEvent
            event.isCancelled = true
        }
    }
}
