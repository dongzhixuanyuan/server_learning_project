package web

import org.springframework.http.converter.json.GsonBuilderUtils
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

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

@RestController
@RequestMapping("/hello")
class RestControllerDemo {

    @RequestMapping("/get")
    fun getHello():String{
        return "server response"
    }

}