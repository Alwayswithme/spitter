package me.phx.controler;

import lombok.extern.java.Log;
import me.phx.mapper.DeviceMapper;
import me.phx.model.Device;
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
@Log
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceMapper deviceMapper;

    @RequestMapping(value = "selectDevicesByOwner", method = RequestMethod.GET)
    public List<Device> selectDevicesByOwner(@RequestParam(value = "id") Integer ownerId) throws IOException {
        List<Device> devices = deviceMapper.selectDevicesByOwner(ownerId);
        return devices;
    }
}
