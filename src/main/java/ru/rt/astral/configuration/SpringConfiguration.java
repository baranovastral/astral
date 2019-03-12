package ru.rt.astral.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@Import({
    DataSourceConfiguration.class,
    SpringSecurityConfiguration.class})
@EnableWebMvc
@ComponentScan(basePackages = {"ru.rt.astral"})
public class SpringConfiguration implements WebMvcConfigurer{
    
    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = 
                new InternalResourceViewResolver("/view/", ".jsp");
        return viewResolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                    .addResourceHandler("/resources/**")
                    .addResourceLocations("/resources/");
    }
    
}
