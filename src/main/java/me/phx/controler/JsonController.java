package me.phx.controler;

import me.phx.model.Pojo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenix
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    @RequestMapping(value = "consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String consume(Pojo pojo) {
        System.out.println(pojo);
        return "ok";
    }
}
