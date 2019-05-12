package ru.mycity.core.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("ru.mycity.core")
public class JpaConfig {

    private static final Logger LOG = LoggerFactory.getLogger(JpaConfig.class);

    @Value("${dataSource.driverClassName}")
    private String driver;
    @Value("${dataSource.url}")
    private String url;
    @Value("${dataSource.username}")
    private String username;
    @Value("${dataSource.password}")
    private String password;

    @Bean
    @Primary
    public DataSource configureDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        if(LOG.isDebugEnabled()){
            LOG.debug(String.format("Main data source is [%s]",url));
        }

        return basicDataSource;

    }


    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



    @Bean
    public TaskScheduler concurrentTaskScheduler(){
//        ConcurrentTaskScheduler scheduler = new ConcurrentTaskScheduler();
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        return scheduler;

    }

}

