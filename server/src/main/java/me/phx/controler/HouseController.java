package me.phx.controler;

import me.phx.mapper.HouseMapper;
import me.phx.model.House;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseMapper houseMapper;

    @RequiresRoles("admin")
    @RequestMapping(value = "selectHouseAsMaps", method = RequestMethod.GET)
    public RestObject selectHouseAsMaps() {
        RestObject r = new RestObject();
        r.setOk(true);
        List<Map<String, Object>> maps = houseMapper.selectHouseAsMaps();
        r.setMaps(maps);
        return r;
    }

    @RequiresAuthentication
    @RequestMapping(value = "selectHouseAsObject", method = RequestMethod.GET)
    public List<House> selectHouseAsObject() {
        return houseMapper.selectAll();
    }

    @RequestMapping(value = "selectHouseWithOwner", method = RequestMethod.GET)
    public List<House> selectHouseWithOwner() {
        return houseMapper.selectHouseWithOwner();
    }


    @RequestMapping(value = "save", method = RequestMethod.GET)
    public House save(@RequestParam String ip) {
        House house = new House();
        house.setLocation("shankala");
//        house.setOwner(personMapper.selectPersonById(1));
        house.setSize(House.Size.SMALL);
        house.setIpAddress(ip);
        int count = houseMapper.insert(house);
        System.out.println("total count : " + count);
        return house;
    }
}
