package me.phx.mybatis.mapper;

import me.phx.model.House;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@CacheNamespace(readWrite = false)  // use readWrite = true need to implements Serializable
public interface HouseMapper {
    @Select({
            "SELECT *",
            "FROM House"
    })
    List<Map<String,Object>> selectHouseAsMaps();

    @Select("SELECT * FROM House ORDER BY id")
    @Results({
            @Result(property = "owner", column = "owner_id", one = @One(select = "PersonMapper.selectOne")),
    })
    List<House> selectHouse();
}
