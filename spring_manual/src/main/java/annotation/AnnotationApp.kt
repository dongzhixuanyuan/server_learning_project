package annotation

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import java.time.ZoneId

/**
 * @description 通过注解写配置
 *
 * @author magina
 * @date 2020/11/14 10:48 下午
 */
@Configuration
@ComponentScan
open class AnnotationApp {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val context = AnnotationConfigApplicationContext(AnnotationApp::class.java)
            val bean = context.getBean(InjectListDemo::class.java)
            bean.animalList?.forEach {
                println(it.javaClass.simpleName)
            }
            context.getBean(ResourceTest::class.java)
            println(context.getBean("createZoneID"))
            val bean1 = context.getBean(MailService::class.java)
            println(bean1.smtpHost+bean1.smtpPort)
            val zoneId = context.getBean(ZoneId::class.java)
            println(zoneId.id)
            val launchAction = context.getBean(LaunchAction::class.java)
//            val uploader = context.getBean(FileUploader::class.java)
//            println(uploader)
        }
    }
}