import annotation.Dog
import annotation.Talk
import event.CStartEventHandler
import event.CStopEventHandler
import event.CustomEventHandler
import event.CustomEventPublisher
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/13 10:25 下午
 */



@Configuration
open class HelloWorldConfig {

    @Bean(initMethod = "initTest")
    open fun dog(): Dog {
        return Dog()
    }

    @Bean
    @Qualifier("bigDog")
    open fun bigDog():Dog{
        return Dog()
    }


    @Bean
    open fun talk(): Talk {
        return Talk()
    }

    @Bean
    open fun startEvent():CStartEventHandler {
        return CStartEventHandler()
    }

    @Bean
    open fun stopEvent(): CStopEventHandler {
        return CStopEventHandler()
    }

    @Bean
    open fun publish(): CustomEventPublisher {
        return CustomEventPublisher()
    }

    @Bean
    open fun customEvent(): CustomEventHandler {
        return CustomEventHandler()
    }

}

@Configuration
@Component
open class ZoneIDFactoryBean:FactoryBean<ZoneID>{
    override fun getObject(): ZoneID? {
        return ZoneID()
    }

    override fun getObjectType(): Class<*>? {
       return ZoneID::class.java
    }
}
class ZoneID {}