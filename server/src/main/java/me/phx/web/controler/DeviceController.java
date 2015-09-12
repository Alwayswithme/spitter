package me.phx.web.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.phx.model.Comment;
import me.phx.mybatis.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author phoenix
 */
@Slf4j
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private DeviceMapper deviceMapper;

//    @RequestMapping(value = "selectDevicesByOwner", method = RequestMethod.GET)
//    public List<Device> selectDevicesByOwner(@RequestParam(value = "id") Integer ownerId) throws IOException {
//        List<Device> devices = deviceMapper.selectDevicesByOwner(ownerId);
//        return devices;
//    }

    @RequestMapping("/test")
    public Comment test() throws JsonProcessingException {
        Comment c = new Comment();
        c.setId(1).setAuthor("hhh");

        String s = objectMapper.writeValueAsString(c);
        return c;
    }
}
