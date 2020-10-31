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
        val connection = DbManager.getConnection()
        var user: User? = null
        try {
            val queryRunner = QueryRunner()
            val user = queryRunner.query(
                connection,
                "select * from user where name = ? and password = ?",
                BeanHandler(User::class.java),
                name,
                password
            )

        } catch (e: Exception) {
        } finally {
            connection.close()
        }
        return user

    }

    override fun userRegister(name: String, password: String, number: String): User? {
        if (name.isEmpty() || password.isEmpty() || number.isNullOrEmpty()) {
            return null
        }
        val connection = DbManager.getConnection()
        var user: User? = null
        try {
            var queryRunner = QueryRunner()
            val success = queryRunner.execute(
                connection,
                "insert into user (name ,password, number )values ('$name','$password','$number')"
            )
            if (success > 0) {
                queryRunner = QueryRunner()
                user = queryRunner.query(
                    connection, "select * from user where name = ? and password = ?",
                    BeanHandler(User::class.java),
                    name,
                    password
                )
            }
        } catch (e: Exception) {

        } finally {
            connection.close()
        }
        return user
    }
}