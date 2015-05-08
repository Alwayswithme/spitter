package com.springapp.mvc;

import com.springapp.mvc.model.Device;
import com.springapp.mvc.repository.DeviceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @author phoenix
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    private static final Logger log = Logger.getLogger(PersonController.class);

    @Autowired
    private DeviceRepository deviceRepository;


    @RequestMapping(value = "selectDevicesByOwner", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Device> selectDevicesByOwner(@RequestParam(value = "id") Integer ownerId) throws IOException {
        log.info(".... Fetching devices");
        List<Device> devices = deviceRepository.getByOwnerId(ownerId);
        log.info("devices -->" + devices);
        return devices;
    }
}