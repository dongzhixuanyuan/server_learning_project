package webmvc.feature.jms

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import javax.jms.Message
import javax.jms.TextMessage


@Component
class MailMessageListener {
    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    var objectMapper: ObjectMapper? = null

    @Autowired
    var mailService: MailService? = null
    @JmsListener(destination = "jms/queue/mail", concurrency = "10")
    @Throws(Exception::class)
    fun onMailMessageReceived(message: Message) {
        logger.info("received message: $message")
        if (message is TextMessage) {
            val text: String = (message as TextMessage).getText()
            val mm = objectMapper!!.readValue(text, MailMessage::class.java)
            mailService!!.sendRegistrationMail(mm)
        } else {
            logger.error("unable to process non-text message!")
        }
    }
}