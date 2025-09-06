package edu.t1.javapro1.hw4.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {

    @Bean
    public HikariConfig hikariConfig(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {

        HikariConfig config = new HikariConfig();

        config.setPoolName("MyHikariPool");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return config;
    }

    @Bean
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public SpringLiquibase liquibase(
            DataSource dataSource,
            @Value("${spring.liquibase.change-log}") String changeLog,
            @Value("${spring.liquibase.default-schema:}") String defaultSchema,
            @Value("${spring.liquibase.drop-first:false}") boolean dropFirst,
            @Value("${spring.liquibase.enabled:true}") boolean enabled) {

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLog);
        liquibase.setDefaultSchema(defaultSchema);
        liquibase.setDropFirst(dropFirst);
        liquibase.setShouldRun(enabled);

        return liquibase;
    }
}
