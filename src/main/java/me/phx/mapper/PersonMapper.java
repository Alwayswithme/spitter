package me.phx.mapper;

import me.phx.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@Component
public interface PersonMapper extends BaseMapper<Person> {

    List<Map<String,Object>> selectPersonAsMaps();
}
