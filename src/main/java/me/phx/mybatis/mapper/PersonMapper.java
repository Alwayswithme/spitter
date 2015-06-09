package me.phx.mybatis.mapper;

import me.phx.model.Person;
import me.phx.mybatis.provider.PersonProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

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
    Person selectPersonById(int id);

    @Select({ "SELECT * FROM Person"})
    @MapKey("id")
    Map<Integer, Person> selectPersonAsMapById();

    @Select({
            "SELECT p.name as personName, p.age as personAge",
            "FROM Person p"
    })
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

    @Delete("DELETE FROM Person WHERE id = #{id}")
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
            @Result(property = "devices", column = "id", many = @Many(select = "me.phx.mybatis.mapper.DeviceMapper.selectDevicesByOwner")),
    })
    Person selectPersonWithDevices(Integer id);

    Person selectPersonJoinDevices(Integer id);

    @SelectProvider(type = PersonProvider.class, method = "personAgeRange")
    List<Person> personAgeGreatThan(Map<String, String> param, RowBounds rowBounds);
}
