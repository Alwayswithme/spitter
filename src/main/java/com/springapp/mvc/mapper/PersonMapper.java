package com.springapp.mvc.mapper;

import com.springapp.mvc.model.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
public interface PersonMapper {
    @Select("SELECT * FROM Person")
    List<Person> selectAll();

    @Select({"SELECT * FROM Person",
            "WHERE id = #{id}"})
    Person selectOne(int id);

    @Select({ "SELECT * FROM Person"})
    @MapKey("id")
    Map<Integer, Person> selectPersonAsMapById();

    @Select({
            "SELECT *",
            "FROM Person"
    })
    List<Map<String,Object>> selectPersonAsMaps();

    @Select("SELECT count(1) FROM Person")
    int count();

    @Insert({
            "INSERT INTO Person",
            "(name, age)",
            "VALUES (#{name}, #{age})"
    })
    @Options(useGeneratedKeys = true, keyProperty="id")
    int insertPerson(Person person);

    @Delete("DELETE FROM Person WHERE id = #{id}")
    int deleteById(int id);

    @Select({
            "SELECT *",
            "FROM Person",
            "WHERE FIND_IN_SET(id, #{ids})"
            })
    List<Person> selectSpecificIds(@Param("ids") String ids);

    @Select({"SELECT * FROM Person",
            "WHERE id = #{id}"})
    @Results({
            @Result(property = "devices", column = "id", many = @Many(select = "com.springapp.mvc.mapper.DeviceMapper.selectDevicesByOwner")),
    })
    Person selectPersonWithDevices(int id);

    Person selectPersonJoinDevices(int id);
}
