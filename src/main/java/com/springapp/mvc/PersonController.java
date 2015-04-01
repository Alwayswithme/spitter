package com.springapp.mvc;

import com.springapp.mvc.mapper.PersonMapper;
import com.springapp.mvc.model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by phoenix on 4/1/15.
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    @RequestMapping(value = "selectAll", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Person> selectAll() throws IOException {
        String resources = "mybatis.xml";
        Reader reader = Resources.getResourceAsReader(resources);
        SqlSessionFactory ssFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession sqlSession = ssFactory.openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            return mapper.selectAll();
        }
    }

    @RequestMapping(value = "selectOne", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Person selectOne(@RequestParam int id) throws IOException {
        String resources = "mybatis.xml";
        Reader reader = Resources.getResourceAsReader(resources);
        SqlSessionFactory ssFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession sqlSession = ssFactory.openSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            return mapper.selectOne(id);
        }
    }
}
