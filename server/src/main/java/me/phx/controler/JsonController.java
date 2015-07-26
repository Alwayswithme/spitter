package me.phx.controler;

import me.phx.model.Device;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenix
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    @RequestMapping(value = "consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String consume(@RequestBody Device device) {
        System.out.println(device);
        return "ok"+ device.toString();
    }
}
