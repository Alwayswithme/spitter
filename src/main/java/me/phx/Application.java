package me.phx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author phoenix
 */
@SpringBootApplication
@ComponentScan
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);

//        for (String s : ctx.getBeanDefinitionNames()) {
//            System.out.println(s);
//        }
    }
}
