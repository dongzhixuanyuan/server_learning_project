package com.liudong.util

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 6:20 下午
 */
object DbManager {
    var datasource:HikariDataSource
    init {
        Class.forName("com.mysql.cj.jdbc.Driver")
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/book_store?useUnicode=true&characterEncoding=UTF-8"
            username = "root"
            password = ""
            addDataSourceProperty("connectionTimeout", "1000")
            addDataSourceProperty("idleTimeout", "60000")
            addDataSourceProperty("maximumPoolSize", "10")
        }
        datasource = HikariDataSource(config)
        print("成功获取数据库连接 ${datasource.toString()}")
    }
}
