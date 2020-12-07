package webmvc.feature.jms

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MailService {

    val logger = LoggerFactory.getLogger(javaClass)

    fun sendRegistrationMail(mm: MailMessage) {
        logger.info("[send mail] sending registration mail to {}...", mm.email)
        // TODO: simulate a long-time task:
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
        }
        logger.info("[send mail] registration mail was sent to {}.", mm.email)
    }

}