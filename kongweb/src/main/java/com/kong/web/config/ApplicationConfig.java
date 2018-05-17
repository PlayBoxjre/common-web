package com.kong.web.config;


import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan(value = "com.kong.web",excludeFilters = {
        @ComponentScan.Filter({EnableWebMvc.class})
})
@ImportResource("classpath:spring/application-*.xml")
public class ApplicationConfig {


    @Bean
    public ViewResolver createViewResolver(){
        InternalResourceViewResolver viewResolver =new  InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setContentType("text/html;charset=utf8");
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setExposedContextBeanNames("rc");
        viewResolver.setExposePathVariables(true);
        return viewResolver;
    }

}
