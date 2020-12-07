package com.magina.antiswindle.user.mapper

import com.magina.antiswindle.user.User

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/7 5:16 下午
 */
interface UserMapper {
    fun register(bean:User):Long
}