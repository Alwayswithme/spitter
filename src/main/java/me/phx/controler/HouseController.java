package me.phx.controler;

import me.phx.model.House;
import me.phx.model.enums.HouseSize;
import me.phx.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "selectHouseAsMaps", method = RequestMethod.GET)
    public List<Map<String,Object>> selectHouseAsMaps() throws IOException {
        return houseMapper.selectHouseAsMaps();
    }

    @RequestMapping(value = "selectHouseAsObject", method = RequestMethod.GET)
    public List<House> selectHouseAsObject() throws IOException {
        return houseMapper.selectHouseAsObject();
    }

    @RequestMapping(value = "selectHouseWithOwner", method = RequestMethod.GET)
    public List<House> selectHouseWithOwner() throws IOException {
        return houseMapper.selectHouseWithOwner();
    }


    @RequestMapping(value = "save", method = RequestMethod.GET)
    public House save(@RequestParam String ip) throws IOException {
        House house = new House();
        house.setLocation("shankala");
//        house.setOwner(personMapper.selectPersonById(1));
        house.setSize(HouseSize.SMALL);
        house.setIpAddress(ip);
        int count = houseMapper.insertHouse(house);
        System.out.println("total count : " + count);
        return house;
    }
}
