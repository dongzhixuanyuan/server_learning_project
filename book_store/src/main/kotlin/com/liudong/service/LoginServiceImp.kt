package com.liudong.service

import com.liudong.entity.User
import com.liudong.repository.LoginRepoImp

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:17 下午
 */
class LoginServiceImp : ILoginService {

    private val loginRepo by lazy {
        LoginRepoImp()
    }

    override fun login(name: String, password: String): User? {
        if (name.isEmpty() || password.isEmpty()) {
            return null
        }
        return loginRepo.login(name, password)
    }

    override fun userRegister(name: String, password: String, number: String):User? {
        if (name.isEmpty() || password.isEmpty()) {
            return null
        }
        return loginRepo.userRegister(name, password, number)
    }
}