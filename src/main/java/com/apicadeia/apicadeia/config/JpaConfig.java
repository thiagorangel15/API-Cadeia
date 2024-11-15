package com.apicadeia.apicadeia.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.apicadeia.apicadeia.repositories")
@ComponentScan(basePackages = "com.apicadeia.apicadeia")
public class JpaConfig {
}
