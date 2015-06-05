package me.phx.controler;

import me.phx.mybatis.mapper.HouseMapper;
import me.phx.model.House;
import org.apache.ibatis.session.SqlSession;
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
    private SqlSession sqlSession;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Map<String,Object>> selectHouseAsMaps() throws IOException {
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);
        return mapper.selectHouseAsMaps();
    }

    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public List<House> selectAll() throws IOException {
        HouseMapper mapper = sqlSession.getMapper(HouseMapper.class);
        return mapper.selectHouse();
    }
}
