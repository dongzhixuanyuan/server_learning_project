package com.liudong.service

import com.liudong.entity.User

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:06 下午
 */
interface ILoginService {

    fun login(name: String, password: String): User?

}