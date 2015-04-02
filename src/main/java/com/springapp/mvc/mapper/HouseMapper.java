package com.springapp.mvc.mapper;

import com.springapp.mvc.model.House;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
public interface HouseMapper {
    @Select({
            "SELECT *",
            "FROM House"
    })
    List<Map<String,Object>> selectHouseAsMaps();

    @Select("SELECT * FROM House ORDER BY id")
    @Results({
            @Result(property = "owner", column = "owner_id", one = @One(select = "com.springapp.mvc.mapper.PersonMapper.selectOne")),
    })
    List<House> selectHouse();
}
