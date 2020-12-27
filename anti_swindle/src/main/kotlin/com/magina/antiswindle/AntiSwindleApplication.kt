package com.magina.antiswindle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
class AntiSwindleApplication

fun main(args: Array<String>) {
    runApplication<AntiSwindleApplication>(*args)
}
