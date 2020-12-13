package com.magina.antiswindle.controller

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.magina.antiswindle.user.User
import com.magina.antiswindle.user.UserService
import org.springframework.beans.BeanUtils
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
    fun registerUser(@RequestBody params: String): Long {
        val user = Gson().fromJson(params, User::class.java)
        return userService!!.registerUser(user)
    }

    @RequestMapping("/test")
    fun registerTest(@RequestBody user: String) {
        println("test")
    }

    @RequestMapping("/query")
    fun query(phone: String): User? {
        return userService!!.queryByPhone(phone)
    }

    @RequestMapping("/update")
    fun update(@RequestBody params: String):Long {
        val fromJson = Gson().fromJson(params, JsonElement::class.java)

        var id:Int? = null
        var phone:String? = null
        var name:String? = null
        fromJson.asJsonObject.entrySet().forEach {
            if (it.key == "id") {
                id = it.value.asInt
//                oldValue = query(it.value.asInt.toString())
            }else if (it.key == "phone") {
//                oldValue!!.phone = it.value.asString
                phone = it.value.asString
            }else if (it.key == "name") {
                name = it.value.asString
            }

        }
        id?.run {
            val oldValue = userService!!.queryById(this)
            oldValue?.run {
                this.phone = phone!!
                this.name = name!!
                return userService!!.update(this)
            }
        }

        return -1
    }

    @RequestMapping("/delete")
    fun delete(id:Int):Long {
        return userService!!.deleteById(id)
    }

}