package com.magina.antiswindle.controller

import com.google.gson.Gson
import com.magina.antiswindle.user.User
import com.magina.antiswindle.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/11 3:26 下午
 */
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    var userService: UserService? = null

    @RequestMapping("/index")
    fun index(): String {
        return "success"
    }

    @RequestMapping("/register")
    fun registerUser( @RequestBody params: String) {
        val user = Gson().fromJson(params, User::class.java)
        userService!!.registerUser(user)
    }

    @RequestMapping("/test")
    fun registerTest(@RequestBody  user:String) {
        println("test")
    }


}