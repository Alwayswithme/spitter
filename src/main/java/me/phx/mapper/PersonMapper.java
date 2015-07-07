package me.phx.mapper;

import me.phx.model.Person;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@Component
public interface PersonMapper {
    @Select("SELECT * FROM Person")
    List<Person> selectAll();

    Person selectPersonById(int id);

    Person selectPersonById(Person person);

    @Select({ "SELECT * FROM Person"})
    @MapKey("id")
    Map<Integer, Person> selectPersonAsMapById();


    List<Map<String,Object>> selectPersonAsMaps();

    @Select("SELECT count(1) FROM Person")
    int count();

    @Insert({
            "INSERT INTO Person",
            "(name, age)",
            "VALUES (#{name}, #{age})"
    })
    @Options(useGeneratedKeys = true, keyProperty="id", flushCache = true)
    Integer insertPerson(Person person);  // Note: once use Options, the default behavior of flushCache will be false

    Integer deleteById(Integer id);

    @Select({
            "SELECT *",
            "FROM Person",
            "WHERE FIND_IN_SET(id, #{ids})"
            })
    List<Person> selectSpecificIds(@Param("ids") String ids);

    @Select({"SELECT * FROM Person",
            "WHERE id = #{id}"})
    @Results({
            @Result(property = "devices", column = "id", many = @Many(select = "me.phx.mapper.DeviceMapper.selectDevicesByOwner")),
    })
    Person selectPersonWithDevices(Integer id);

    Person selectPersonJoinDevices(Integer id);

    List<Person> personAgeGreatThan(@Param("param")Map<String, Object>param, RowBounds rowBounds);
}
