package db.mybatis

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import db.mybatis.mapper.UserMapper
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource


@Configuration
@ComponentScan
@MapperScan("db.mybatis.mapper")
@EnableTransactionManagement
@PropertySource("jdbc.properties")
open class MyBatisConfig {

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
    open fun createSqlSessionFactoryBean(@Autowired dataSource: DataSource?): SqlSessionFactoryBean? {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)
        return sqlSessionFactoryBean
    }

    @Bean
    open fun createTxManager(@Autowired dataSource: DataSource?): PlatformTransactionManager? {
        return DataSourceTransactionManager(dataSource)
    }
}


@Component
@Transactional
open class UserService {
    // 注入UserMapper:
    @Autowired
    var userMapper: UserMapper? = null

    fun getUserById(id: Long): User {
        // 调用Mapper方法:
        return userMapper!!.getById(id) ?: throw RuntimeException("User not found by id.")
    }
}

fun main() {
    val context = AnnotationConfigApplicationContext(MyBatisConfig::class.java)
    val services = context.getBean(UserService::class.java)
    services.getUserById(199)
}