package com.kong.web.config;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/*@Configuration
@EnableWebMvc
@ComponentScan(value = "com.kong.web.supports",excludeFilters = {
        @ComponentScan.Filter({EnableWebMvc.class})
})*/
public class RootConfig extends WebMvcConfigurationSupport {





    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
    }
}
