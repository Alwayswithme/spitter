package me.phx.web.controler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/welcome")
    public String home() {
        return "welcome";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "please login";
    }

    @RequestMapping(value = "test")
    public void run3() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/login");
        String url = builder.build().encode().toString();

        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername("guest");
        token.setPassword("guest".toCharArray());
        token.setRememberMe(false);
        restTemplate.postForLocation(url, token);
        return ;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginPost(UsernamePasswordToken token) {
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            return;
        }
        //collect user principals and credentials in a gui specific manner
        //such as username/password html form, X509 certificate, OpenID, etc.
        //We'll use the username/password example here since it is the most common.
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //this is all you have to do to support 'remember me' (no config - built in!):
//            token.setRememberMe(rememberMe);

        try {
            currentUser.login(token);


            //print their identifying principal (in this case, a username):
            log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
        } catch (UnknownAccountException uae) {
            //username wasn't in the system, show them an error message?
            log.error(uae.getMessage(), uae);
        } catch (IncorrectCredentialsException ice) {
            //password didn't match, try again?
            log.error(ice.getMessage(), ice);
        } catch (LockedAccountException lae) {
            //account for that username is locked - can't login.  Show them a message?
            log.error(lae.getMessage(), lae);
        } catch (AuthenticationException ae) {
            //unexpected condition - error?
            log.error(ae.getMessage(), ae);
        }
        //... more types exceptions to check if you want ...

        if (currentUser.hasRole("schwartz")) {
            log.info("May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }


        if (currentUser.isPermitted("lightsaber:weild")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            log.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

    }

    @RequestMapping("/checkLogin")
    public boolean checkLogin() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.isAuthenticated();
    }
}
