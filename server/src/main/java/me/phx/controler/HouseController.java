package me.phx.controler;

import me.phx.mapper.HouseMapper;
import me.phx.model.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @RequestMapping(value = "selectHouseAsMaps", method = RequestMethod.POST)
    public RestObject selectHouseAsMaps() throws IOException {
        RestObject r = new RestObject();
        r.setOk(true);
        List<Map<String, Object>> maps = houseMapper.selectHouseAsMaps();
        r.setMaps(maps);
        return r;
    }

    @RequestMapping(value = "selectHouseAsObject", method = RequestMethod.POST)
    public List<House> selectHouseAsObject() throws IOException {
        return houseMapper.selectAll();
    }

    @RequestMapping(value = "selectHouseWithOwner", method = RequestMethod.POST)
    public List<House> selectHouseWithOwner() throws IOException {
        return houseMapper.selectHouseWithOwner();
    }


    @RequestMapping(value = "save", method = RequestMethod.GET)
    public House save(@RequestParam String ip) throws IOException {
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
