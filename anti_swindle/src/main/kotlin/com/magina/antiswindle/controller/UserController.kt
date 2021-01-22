package com.magina.antiswindle.controller

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.magina.antiswindle.user.User
import com.magina.antiswindle.user.UserService
import com.magina.antiswindle.security.TokenProvider
import com.magina.antiswindle.security.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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

    @Autowired
    var authenticationManager: AuthenticationManager? = null

    @Autowired
    var userDetailService: CustomUserDetailsService? = null

    @Autowired
    var tokenProvider: TokenProvider? = null

    @RequestMapping("/index")
    fun index(): String {
        return "success"
    }

    @RequestMapping("/register")
    fun registerUser(@RequestBody params: String): Long {
        val user = Gson().fromJson(params, User::class.java)
        return userService!!.registerUser(user)
    }

    @RequestMapping("/login")
    fun login(@RequestParam username: String, @RequestParam password: String): String {
        kotlin.runCatching {
            val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(username, password)
            val authenticate = authenticationManager!!.authenticate(usernamePasswordAuthenticationToken)
            SecurityContextHolder.getContext().authentication = authenticate
            val details = userDetailService!!.loadUserByUsername(username)
            val tokenObj = tokenProvider!!.createToken(details)
            val tokenJson = Gson().toJson(tokenObj)
            return tokenJson
        }.onFailure {
            return "fail:密码错误"
        }
        return "fail:登录失败"
    }


    @RequestMapping("/test")
    fun registerTest(@RequestBody user: String) {
        println("test")
    }

    @RequestMapping("/query")
    fun query(phone: String): User? {
        return userService!!.queryByPhone(phone)
    }

    @RequestMapping("/querybyname")
    fun queryByName(@RequestParam username: String): User? {
        val user = userService!!.queryByName(username)
        return user
    }


    @RequestMapping("/update")
    fun update(@RequestBody params: String): Long {
        val fromJson = Gson().fromJson(params, JsonElement::class.java)

        var id: Int? = null
        var phone: String? = null
        var name: String? = null
        fromJson.asJsonObject.entrySet().forEach {
            if (it.key == "id") {
                id = it.value.asInt
//                oldValue = query(it.value.asInt.toString())
            } else if (it.key == "phone") {
//                oldValue!!.phone = it.value.asString
                phone = it.value.asString
            } else if (it.key == "name") {
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
    fun delete(id: Int): Long {
        return userService!!.deleteById(id)
    }

}