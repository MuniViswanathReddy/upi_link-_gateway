package com.bank.baroda.app;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Set;

public class WebServletInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("com.*");

        DispatcherServlet dispatcherServlet = new DispatcherServlet((context));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", dispatcherServlet);
        dispatcher.addMapping("/api/*");
        dispatcher.setLoadOnStartup(1);

       /* ServletRegistration.Dynamic dispatcher = servletContext.addServlet("first", FirstServlet.class);
        dispatcher.addMapping("/api/*");
        dispatcher.setLoadOnStartup(1);*/
    }
}