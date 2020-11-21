package db.jpa

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceContext
import javax.persistence.TransactionRequiredException
import javax.sql.DataSource


/**
 * @description 文件描述
 *
 * @author
 * @date 2020/11/21 12:10 下午
 */

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("jdbc.properties")
open class JpaConfig {

    @Value("\${jdbc.url}")
    var jdbcUrl: String? = null

    @Value("\${jdbc.username}")
    var jdbcUsername: String? = null

    @Value("\${jdbc.password}")
    var jdbcPassword: String? = null

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
    open fun createEntityManagerFactory(@Autowired dataSource: DataSource?): LocalContainerEntityManagerFactoryBean? {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        // 设置DataSource:
        entityManagerFactoryBean.dataSource = dataSource
        // 扫描指定的package获取所有entity class:
        entityManagerFactoryBean.setPackagesToScan("com.itranswarp.learnjava.entity")
        // 指定JPA的提供商是Hibernate:
        val vendorAdapter: JpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.jpaVendorAdapter = vendorAdapter
        // 设定特定提供商自己的配置:
        val props = Properties()
        props.setProperty("hibernate.hbm2ddl.auto", "update")
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
        props.setProperty("hibernate.show_sql", "true")
        entityManagerFactoryBean.setJpaProperties(props)
        return entityManagerFactoryBean
    }

    @Bean
    open fun createTxManager(@Autowired entityManagerFactory: EntityManagerFactory?): PlatformTransactionManager? {
        return JpaTransactionManager(entityManagerFactory)
    }


}

@Component
@Transactional
open class UserService() {

    @PersistenceContext var em: EntityManager? = null

    fun getUserById(id: Long): User? {
        val user = em!!.find(User::class.java, id)
        return user
    }

    @Throws(IllegalArgumentException::class, TransactionRequiredException::class)
    fun registerUser(email: String, name: String, password: String) {
        User().apply {
            this.email = email
            this.name = name
            this.password = password
            this.jaofjai = 383993
        }.run {
            em!!.persist(this)
        }
    }

}

fun main() {
    val context = AnnotationConfigApplicationContext(JpaConfig::class.java)
    val services = context.getBean(UserService::class.java)
    services.registerUser("riwoaii444", "fafpao", "fafafa")
}