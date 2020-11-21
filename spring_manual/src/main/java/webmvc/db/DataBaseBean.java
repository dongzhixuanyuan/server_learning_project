package webmvc.db;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Component
@PropertySource("classpath:spring_mvc_jdbc.properties")
public class DataBaseBean {
    @Value("${jdbc.url}")
    String jdbcUrl;
    @Value("${jdbc.username}")
    String userName;
    @Value("${jdbc.password}")
    String password;

    @Bean("hibernate")
    public DataSource createDatasource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);
        hikariConfig.addDataSourceProperty("autoCommit", "true");
        hikariConfig.addDataSourceProperty("connectionTimeout", "5");
        hikariConfig.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalSessionFactoryBean createSessionFactory(@Qualifier("hibernate") @Autowired DataSource dataSource) {
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "update"); // 生产环境不要使用
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect"); //这个要注意，不然出现type=MYISAM错误
        props.setProperty("hibernate.show_sql", "true");
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        // 扫描指定的package获取所有entity class:
        sessionFactoryBean.setPackagesToScan("webmvc.entity");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTemplate createHibernateTemplate(@Autowired SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @Bean
    public PlatformTransactionManager createTxManager(@Autowired SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
