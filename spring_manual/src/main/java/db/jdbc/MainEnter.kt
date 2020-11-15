package db.jdbc

import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/15 5:16 下午
 */

fun main(args: Array<String>) {
    val annotationConfigApplicationContext = AnnotationConfigApplicationContext(JDBCAppConfig::class.java)
    var bean = annotationConfigApplicationContext.getBean(UserService::class.java)
    val user = bean.getUserById(100)
    user?.run {
        println(user.toString())
    }
//    val register = bean.register(90, "7fafaj@ffaf", "fjaisjfa", "fjaisjfao")
//    println(register)
}