package annotation

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.stereotype.Component
import java.time.ZoneId
import javax.annotation.PostConstruct


/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/15 10:01 上午
 */
@Component
@PropertySource("app.properties")
class AppConfig {
    @Value("\${app.zone:Z}")
    var zoneID: String? = null

    @Bean
    fun createZoneID(): String? {
        return zoneID
    }

    @Bean
    fun createSpeZoneId(@Value("\${app.zone:Z}") zoneid: String): String {
        return zoneid
    }

    //    test表示测试环境
    @Bean
    @Profile("!test")
    fun createZoneId(): ZoneId {
        return ZoneId.systemDefault()
    }

    @Bean
    @Profile("test")
    fun createZoneIdForTest(): ZoneId {
        return ZoneId.of("America/New_York")
    }

}

@Component
class MailService {
    @Value("#{smtpConfig.host}")
    val smtpHost: String? = null

    @Value("#{smtpConfig.port}")
    val smtpPort = 0
}

@Component
class SmtpConfig(val host: String = "localhost", val port: Int = 8080)

@Component
@Conditional(LaunchCondition::class)
class LaunchAction {

    @PostConstruct
    fun initMethod(){
        println("LaunchAction Init")
    }
}

class LaunchCondition : Condition {

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return "true" == System.getenv("condition")
    }

}



