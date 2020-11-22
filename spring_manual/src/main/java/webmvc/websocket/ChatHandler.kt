package webmvc.websocket

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import webmvc.entity.ChatMsg
import java.util.concurrent.ConcurrentHashMap


@Component
open class ChatHandler: TextWebSocketHandler() {
    // 保存所有Client的WebSocket会话实例:
    private val clients: MutableMap<String, WebSocketSession> = ConcurrentHashMap()

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        // 新会话根据ID放入Map:
        clients[session.id] = session
        session.attributes["name"] = "Guest1"
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus?) {
        clients.remove(session.id)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        val msg = ChatMsg().apply {
            timestamp = System.currentTimeMillis()
            name = session.attributes["name"] as String
            text = payload
        }
        broadcastMessage(msg); // 推送给所有用户
    }

    private fun broadcastMessage(msg: ChatMsg) {
        clients.forEach { t, u ->
            u.sendMessage(TextMessage(msg.toString()))
        }
    }

}