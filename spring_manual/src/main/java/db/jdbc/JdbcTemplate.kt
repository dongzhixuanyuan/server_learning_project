package db.jdbc

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.scheduling.support.SimpleTriggerContext
import org.springframework.stereotype.Component
import java.sql.Connection
import javax.annotation.PostConstruct
import javax.sql.DataSource


/**
 * @description 文件描述
 *
 * @author magina
 * @date 2020/11/15 5:09 下午
 */
@Configuration
@ComponentScan
@PropertySource("jdbc.properties")
open class JDBCAppConfig {
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
    open fun createJdbcTemplate(@Autowired dataSource: DataSource?): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}

@Component
class DatabaseInitializer() {
    @Autowired
    var jdbcTemplate: JdbcTemplate? = null
    @PostConstruct
    fun init() {
        jdbcTemplate!!.update(
            "CREATE TABLE IF NOT EXISTS users (" //
                    + "id BIGINT IDENTITY NOT NULL PRIMARY KEY, " //
                    + "email VARCHAR(100) NOT NULL, " //
                    + "password VARCHAR(100) NOT NULL, " //
                    + "name VARCHAR(100) NOT NULL, " //
                    + "UNIQUE (email))"
        )

//        jdbcTemplate!!.update("insert into users (id,email,password,name) values (100,'37883@qq.com','3783','Jim')")
    }
}

data class User(val id:Long, val email:String, val password:String, val name:String)

@Component
 class UserService {
    @Autowired
    lateinit var jdbcTemplate:JdbcTemplate

    fun getUserById(id: Long): User? {
        // 注意传入的是ConnectionCallback:
        return jdbcTemplate.execute { conn: Connection ->
            conn.prepareStatement("SELECT * FROM users WHERE id = ?").let { ps ->
                ps.setObject(1, id)
                ps.executeQuery().let {rs->
                    if (rs.next()) {
                        return@execute User( // new User object:
                            rs.getLong("id"),  // id
                            rs.getString("email"),  // email
                            rs.getString("password"),  // password
                            rs.getString("name")
                        ) // name
                    }
                    throw RuntimeException("user not found by id.")
                }

            }
        }
    }
}