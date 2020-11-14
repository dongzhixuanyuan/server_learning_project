import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/13 10:25 下午
 */



@Configuration
open class HelloWorldConfig {

    @Bean(initMethod = "initTest")
    open fun dog():Dog{
        return Dog()
    }

    @Bean
    open fun talk():Talk {
        return Talk()
    }


}