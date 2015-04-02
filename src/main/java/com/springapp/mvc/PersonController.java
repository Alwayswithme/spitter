package com.springapp.mvc;

import com.springapp.mvc.mapper.PersonMapper;
import com.springapp.mvc.model.Person;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value = "selectAll", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Person> selectAll() throws IOException {
        return sqlSession.selectList("com.springapp.mvc.mapper.PersonMapper.selectAll");
    }

    @RequestMapping(value = "selectById/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Person selectOne(@PathVariable int id) throws IOException {
        return sqlSession.selectOne("com.springapp.mvc.mapper.PersonMapper.selectOne", id);
    }

    @RequestMapping(value = "selectAllAsMap", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<Integer, Person> selectAllAsMap() throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.selectPersonAsMapById();

//        one-liner
//        return sqlSession.selectMap("com.springapp.mvc.mapper.PersonMapper.selectAll", "age");
    }

    /**
     * type-safe and more readable way
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Map<String,Object>> selectPersonAsMaps() throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.selectPersonAsMaps();
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public @ResponseBody int count() throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.count();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(value = "insert", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Person insert(@RequestParam String name, @RequestParam int age) throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(name, age);
        mapper.insertPerson(person);

        return person;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(value = "deleteById/{id}", method = RequestMethod.GET)
    public @ResponseBody int deleteOne(@PathVariable int id) throws IOException {
        int row = sqlSession.delete("com.springapp.mvc.mapper.PersonMapper.deleteById", id);
        // manually rollback
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return row;
    }
}
