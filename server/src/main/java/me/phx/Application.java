package me.phx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

/**
 * @author phoenix
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(Application.class, args);

        for (String s : ctx.getBeanDefinitionNames()) {
            System.out.println(s);
        }
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
