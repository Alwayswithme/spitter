package me.phx.mapper;

import me.phx.model.House;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@Component
public interface HouseMapper extends BaseMapper<House>  {
    List<Map<String,Object>> selectHouseAsMaps();

    List<House> selectHouseWithOwner();
}
