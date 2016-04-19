package me.phx.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ye
 */
@RestController
public class ResourceController {

    @RequestMapping("/user/profile")
    public Principal getUserProfile(Principal principal) {
        return principal;
    }
}