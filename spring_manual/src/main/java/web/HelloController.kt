package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/14 5:28 下午
 */
@Controller
@RequestMapping("/springmvc")
class HelloWorldController {
    @RequestMapping("/helloworld")
    fun sayHello(): String {
        println("Hello World!")
        return "hello"
    }
}