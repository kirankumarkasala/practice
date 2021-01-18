package com.excelra.glpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot application for Main and Tomcat execution.
 *
 */
@SpringBootApplication
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
public class GlpgService extends SpringBootServletInitializer {
    public static void main(String[] args)
    {
        SpringApplication.run(GlpgService.class, args);
    }

    /**
     * Used when run as WAR
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GlpgService.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    
}
