package com.springapp.mvc.mapper;

import com.springapp.mvc.model.Device;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author phoenix
 */
public interface DeviceMapper {
    @Select("SELECT * FROM Device WHERE owner_id = #{ownerId}")
    List<Device> selectDevicesByOwner(int ownerId);

    @Select({"SELECT * FROM Device",
            "WHERE FIND_IN_SET(id, #{ids})"
            })
    @Results({
        @Result(property = "owner", column = "owner_id", one = @One(select = "com.springapp.mvc.mapper.PersonMapper.selectOne"))
    })
    List<Device> selectDevicesByIds(int ids);

    @Update("UPDATE Device SET name = #{name}, owner_id = #{owner.id}, type = #{type} WHERE id = #{id}")
    Integer updateDevice(Device device);
}
