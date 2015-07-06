package me.phx.mybatis.mapper;

import me.phx.model.House;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@Component
public interface HouseMapper {

    List<Map<String,Object>> selectHouseAsMaps();

    List<House> selectHouseAsObject();

    List<House> selectHouseWithOwner();

    int insertHouse(House house);
}
