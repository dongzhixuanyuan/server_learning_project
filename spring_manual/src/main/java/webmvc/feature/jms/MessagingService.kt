package webmvc.feature.jms

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component


@Component
open class MessagingService {

    @Autowired
    var objectMapper: ObjectMapper? = null

    @Autowired
    var jmsTemplate: JmsTemplate? = null
    @Throws(Exception::class)
    fun sendMailMessage(msg: MailMessage?) {
        val text: String = objectMapper!!.writeValueAsString(msg)
        jmsTemplate!!.send(
            "jms/queue/mail"
        ) { session -> session.createTextMessage(text) }
    }
}