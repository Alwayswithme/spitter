package me.phx.controler;

import me.phx.model.House;
import me.phx.model.HouseSize;
import me.phx.mybatis.mapper.HouseMapper;
import me.phx.mybatis.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @Autowired
    private PersonMapper personMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Map<String,Object>> selectHouseAsMaps() throws IOException {
        return houseMapper.selectHouseAsMaps();
    }

    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public List<House> selectAll() throws IOException {
        return houseMapper.selectHouse();
    }

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public House save() throws IOException {
        House house = new House();
        house.setLocation("shankala");
//        house.setOwner(personMapper.selectPersonById(1));
        house.setSize(HouseSize.SMALL);
        int count = houseMapper.insertHouse(house);
        System.out.println("total count : " + count);
        return house;
    }
}
