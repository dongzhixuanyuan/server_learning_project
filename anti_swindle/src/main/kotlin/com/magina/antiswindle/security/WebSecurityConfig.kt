package com.magina.antiswindle.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/11 3:30 下午
 */
@Configuration
class WebSecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(auth: AuthenticationManagerBuilder?) {
        super.configure(auth)
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().anyRequest().permitAll()
    }

    override fun configure(web: WebSecurity?) {
        super.configure(web)
    }
}