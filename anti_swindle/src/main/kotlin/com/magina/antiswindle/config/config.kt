package com.magina.antiswindle

import com.magina.antiswindle.const.Env
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.text.DateFormat
import java.util.*

/**
 * @description 配置图片文件在电脑上的位置
 *
 * @author magina (735106520@qq.com)
 * @date 2021/1/16 7:03 下午
 */
@Configuration
class MyWebMvcConfigurerAdapter : WebMvcConfigurer {
    /**
     * 配置静态访问资源
     * @param registry
     */
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/media/**").addResourceLocations("file:${Env.BASE_DIR}")
        super.addResourceHandlers(registry)
    }
}

