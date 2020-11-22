package com.liudong.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import java.util.Map
import java.util.concurrent.Callable


/**
 * @description 文件描述
 *
 * @author liudong (735106520@qq.com)
 * @date 2020/11/22 10:22 上午
 */
@RestController()
@RequestMapping("/user")
open class UserController {


    @RequestMapping("/get")
    open fun getUsers():Callable<String>{
        return Callable<String> {
            Thread.sleep(3000)
            "get user success"
        }
    }

    @GetMapping("/get/{id}")
    open fun user(@PathVariable("id") id: Long): DeferredResult<String>? {
        val result: DeferredResult<String> = DeferredResult<String>(3000L) // 3秒超时
        Thread {

            // 等待1秒:
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
            }
            try {
                // 设置正常结果并由Spring MVC写入Response:
                result.setResult("success")
            } catch (e: Exception) {
                // 设置错误结果并由Spring MVC写入Response:
                result.setErrorResult("fail")
            }
        }.start()
        return result
    }

}