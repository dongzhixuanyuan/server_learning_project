package event

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextStartedEvent
import org.springframework.context.event.ContextStoppedEvent

/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/14 12:26 下午
 */


class CStartEventHandler : ApplicationListener<ContextStartedEvent> {

    override fun onApplicationEvent(event: ContextStartedEvent) {
        println("ContextStartedEvent")
    }

}

class CStopEventHandler : ApplicationListener<ContextStoppedEvent> {
    override fun onApplicationEvent(event: ContextStoppedEvent) {
        print("ContextStoppedEvent")
    }

}

class CustomEvent(source: Any) : ApplicationEvent(source) {
    override fun toString(): String {
        return "My custom event"
    }
}

class CustomEventHandler : ApplicationListener<CustomEvent> {
    override fun onApplicationEvent(event: CustomEvent) {
        println(event.toString())
    }

}

class CustomEventPublisher(var publisher: ApplicationEventPublisher? = null) : ApplicationEventPublisherAware {

    constructor():this(null)

    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        publisher = applicationEventPublisher
    }

    fun publish() {
        publisher?.publishEvent(CustomEvent(this))
    }
}



