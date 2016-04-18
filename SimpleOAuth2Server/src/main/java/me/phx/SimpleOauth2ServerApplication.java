package me.phx;

import me.phx.data.entity.Role;
import me.phx.data.entity.User;
import me.phx.data.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.Arrays;

@EnableResourceServer
@SpringBootApplication
public class SimpleOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleOauth2ServerApplication.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository) throws Exception {
        if (repository.count()==0) {
            repository.save(new User("user", "123456", Arrays.asList(new Role("USER"))));
        }

        UserDetailsService service = username -> new CustomUserDetails(repository.findByUsername(username));
        builder.userDetailsService(service);
    }
}
