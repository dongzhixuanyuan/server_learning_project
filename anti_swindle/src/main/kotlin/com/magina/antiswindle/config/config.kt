package com.magina.antiswindle

import com.magina.antiswindle.const.Env
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.text.SimpleDateFormat
import java.util.*

/**
 * @description 配置图片文件在电脑上的位置
 *
 * @author magina (735106520@qq.com)
 * @date 2021/1/16 7:03 下午
 */
@Configuration
class MyWebMvcConfigurerAdapter : WebMvcConfigurer {

    @Autowired
    var env:Env? = null;
    /**
     * 配置静态访问资源
     * @param registry
     */
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/video/**").addResourceLocations("file:${env!!.BASE_VIDEO_DIR}")
        registry.addResourceHandler("/image/**").addResourceLocations("file:${env!!.BASE_IMAGE_DIR}")
        super.addResourceHandlers(registry)
    }
}

@ConfigurationProperties("res.store.path")
@Component
open class DynamicConfig {
    var img:String? = null
    var video:String? = null

}

//fun main() {
//    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:MM:ss")
//    val format = simpleDateFormat.format(Calendar.getInstance().time)
//    print(format)
//}

