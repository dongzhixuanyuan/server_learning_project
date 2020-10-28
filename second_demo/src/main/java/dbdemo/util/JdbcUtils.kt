package dbdemo.util

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dbdemo.entity.Student
import dbdemo.repositoty.StudentRepository
import java.sql.Connection
import java.sql.Date
import java.sql.DriverManager

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/27 11:20 上午
 */
object JdbcUtils {

    var datasource:HikariDataSource

    init {
        Class.forName("com.mysql.cj.jdbc.Driver")
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/TEST_DB?useUnicode=true&characterEncoding=UTF-8"
            username = "root"
            password = ""
            addDataSourceProperty("connectionTimeout", "1000")
            addDataSourceProperty("idleTimeout", "60000")
            addDataSourceProperty("maximumPoolSize", "10")
        }
        datasource = HikariDataSource(config)
//        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TEST_DB?useUnicode=true&characterEncoding=UTF-8", "root", "")
        print("成功获取数据库连接 ${datasource.toString()}")
    }

    fun test() {

    }
}

fun main() {
    val studentRepository = StudentRepository()
    studentRepository.addStudent(Student(2,"对象",90.3))
}