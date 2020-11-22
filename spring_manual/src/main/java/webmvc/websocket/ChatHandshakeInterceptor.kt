package webmvc.websocket

import org.springframework.stereotype.Component
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor

@Component
class ChatHandshakeInterceptor : HttpSessionHandshakeInterceptor {

    constructor():super(listOf("good","bad"))

}