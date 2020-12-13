package com.magina.antiswindle.user

import com.magina.antiswindle.user.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/7 5:12 下午
 */

@Component
class UserService {

    @Autowired
    var userMapper:UserMapper? = null

    fun registerUser(user: User):Long {
      return userMapper!!.register(user)
    }

    fun queryByPhone(phone:String):User?{
        return userMapper!!.queryByPhone(phone)
    }

    fun queryById(id:Int):User?{
        return userMapper!!.queryById(id)
    }

    fun update(user: User):Long{
        return userMapper!!.update(user)
    }

    fun deleteById(id: Int):Long {
        return userMapper!!.delete(id)
    }




}