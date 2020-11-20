package db.hibernate

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource


/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/15 9:33 下午
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("jdbc.properties")
open class HibernateConfig {

    @Value("\${jdbc.url}")
    var jdbcUrl:String? = null

    @Value("\${jdbc.username}")
    var jdbcUsername:String? = null

    @Value("\${jdbc.password}")
    var jdbcPassword:String? = null

    @Bean
    open fun createDataSource(): DataSource {
        val config = HikariConfig()
        config.setJdbcUrl(jdbcUrl)
        config.setUsername(jdbcUsername)
        config.setPassword(jdbcPassword)
        config.addDataSourceProperty("autoCommit", "true")
        config.addDataSourceProperty("connectionTimeout", "5")
        config.addDataSourceProperty("idleTimeout", "60")
        return HikariDataSource(config)
    }

    @Bean
    open fun createSessionFactory(@Autowired dataSource: DataSource?): LocalSessionFactoryBean? {
        val props = Properties()
        props.setProperty("hibernate.hbm2ddl.auto", "update") // 生产环境不要使用
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
        props.setProperty("hibernate.show_sql", "true")
        val sessionFactoryBean = LocalSessionFactoryBean()
        sessionFactoryBean.setDataSource(dataSource)
        // 扫描指定的package获取所有entity class:
        sessionFactoryBean.setPackagesToScan("db.hibernate")
        sessionFactoryBean.hibernateProperties = props
        return sessionFactoryBean
    }

    @Bean
    open fun createHibernateTemplate(@Autowired sessionFactory: SessionFactory?): HibernateTemplate? {
        return HibernateTemplate(sessionFactory)
    }

    @Bean
    open fun createTxManager(@Autowired sessionFactory: SessionFactory?): PlatformTransactionManager? {
        return HibernateTransactionManager(sessionFactory)
    }
}

fun main() {
    val annotationConfigApplicationContext = AnnotationConfigApplicationContext(HibernateConfig::class.java)
    val bean = annotationConfigApplicationContext.getBean(UserServices::class.java)
    bean.register("8393898@fff.com","ffijajf","jack")
    println("fafa")
    bean.register("123456","faoisjfao","maker")
    val login = bean.login("123456", "faoisjfao")
    println(login!!)
}