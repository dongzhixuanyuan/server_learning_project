package com.liudong.repository

import com.liudong.entity.User
import com.liudong.util.DbManager
import org.apache.commons.dbutils.DbUtils
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanHandler

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:20 下午
 */
class LoginRepoImp : ILoginRepo {


    override fun login(name: String, password: String): User? {
        if (name.isEmpty() || password.isEmpty()) {
            return null
        }
        val connection = DbManager.datasource.connection
        val queryRunner = QueryRunner()
        val user = queryRunner.query(
            connection,
            "select * from user where name = ? and password = ?",
            BeanHandler(User::class.java),
            name,
            password
        )
        connection.close()
        return user

    }
}