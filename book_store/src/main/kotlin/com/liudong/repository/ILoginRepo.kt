package com.liudong.repository

import com.liudong.entity.User

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:19 下午
 */
interface ILoginRepo {
    fun login(name: String, password: String): User?

}