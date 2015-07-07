package me.phx.controler;

import me.phx.model.Device;
import me.phx.mapper.DeviceMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author phoenix
 */
@RestController
@RequestMapping("/device")
public class DeviceController {
    private static final Logger log = Logger.getLogger(PersonController.class);

    @Autowired
    private DeviceMapper deviceMapper;

    @RequestMapping(value = "selectDevicesByOwner", method = RequestMethod.GET)
    public List<Device> selectDevicesByOwner(@RequestParam(value = "id") Integer ownerId) throws IOException {
        List<Device> devices = deviceMapper.selectDevicesByOwner(ownerId);
        return devices;
    }
}
