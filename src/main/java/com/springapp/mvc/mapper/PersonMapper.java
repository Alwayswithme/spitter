package com.springapp.mvc.mapper;

import com.springapp.mvc.model.Person;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

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
}
