package com.magina.antiswindle.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AntiSwindleApplication

fun main(args: Array<String>) {
    runApplication<AntiSwindleApplication>(*args)
}
