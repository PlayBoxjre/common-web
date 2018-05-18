package com.kong.web.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan(value = "com.kong.web",excludeFilters = {
        @ComponentScan.Filter({EnableWebMvc.class})
})
public class ApplicationConfig {
}
