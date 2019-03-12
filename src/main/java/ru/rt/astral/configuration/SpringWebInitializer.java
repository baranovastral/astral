package ru.rt.astral.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support
        .AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) 
            throws ServletException {
        AnnotationConfigWebApplicationContext context = 
                new AnnotationConfigWebApplicationContext();
        context.register(SpringConfiguration.class);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "SpringDispatcher",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setInitParameter(
                "contextClass", 
                context.getClass().getName());
        servletContext.addListener(new ContextLoaderListener(context));
    }
    
}
