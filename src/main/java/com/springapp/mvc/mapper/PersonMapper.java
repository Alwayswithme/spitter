package com.springapp.mvc.mapper;

import com.springapp.mvc.model.Person;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by phoenix on 3/31/15.
 */
public interface PersonMapper {
    @Select("SELECT * FROM Person")
    List<Person> selectAll();

    @Select({"SELECT * FROM Person",
            "WHERE id = #{id}"})
    Person selectOne(int id);
}
