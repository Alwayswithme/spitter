package me.phx.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author phoenix
 */
public class SpitterInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
//        appContext.setConfigLocation("/WEB-INF/mvc-dispatcher-servlet.xml");
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(DispatcherConfig.class);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("mvc-dynamic", new DispatcherServlet(appContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }
}
