package com.springapp.mvc.controler;

import com.springapp.mvc.mapper.PersonMapper;
import com.springapp.mvc.model.Person;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value = "selectAll", method = RequestMethod.GET, produces = "application/json")
    public List<Person> selectAll() throws IOException {
        return sqlSession.selectList("com.springapp.mvc.mapper.PersonMapper.selectAll");
    }

    @RequestMapping(value = "selectById/{id}", method = RequestMethod.GET, produces = "application/json")
    public Person selectPersonWithDevices(@PathVariable int id) {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
//        return mapper.selectPersonWithDevices(id);         // use annotation

        return mapper.selectPersonJoinDevices(id);  // use xml mapper
    }

    @RequestMapping(value = "selectAllAsMap", method = RequestMethod.GET, produces = "application/json")
    public Map<Integer, Person> selectAllAsMap() throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.selectPersonAsMapById();

//        one-liner
//        return sqlSession.selectMap("com.springapp.mvc.mapper.PersonMapper.selectAll", "age");
    }

    /**
     * type-safe and more readable way
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public List<Map<String,Object>> selectPersonAsMaps() throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.selectPersonAsMaps();
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public int count() throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.count();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(value = "insert", method = RequestMethod.GET, produces = "application/json")
    public Person insert(@RequestParam String name, @RequestParam int age) throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person person = new Person(name, age);
        mapper.insertPerson(person);

        return person;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(value = "deleteById/{id}", method = RequestMethod.GET)
    public int deleteOne(@PathVariable int id) throws IOException {
        int row = sqlSession.delete("com.springapp.mvc.mapper.PersonMapper.deleteById", id);
        // manually rollback
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return row;
    }

    @RequestMapping(value = "selectByIds", method = RequestMethod.GET)
    public List<Person> selectByIds(@RequestParam(value = "ids") String ids) throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.selectSpecificIds(ids);
    }

    @RequestMapping(value = "ageRange", method = RequestMethod.GET)
    public List<Person> age(@RequestParam Map<String, String> params,
                                          @RequestParam(required = false) Integer limit,
                                          @RequestParam(required = false) Integer offset) {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        RowBounds rowBounds = RowBounds.DEFAULT;
        if (params.containsKey("limit") && params.containsKey("offset")) {
            rowBounds = new RowBounds(offset, limit);
        } else if (params.containsKey("offset")) {
            rowBounds = new RowBounds(offset, RowBounds.NO_ROW_LIMIT);
        } else if (params.containsKey("limit")) {
            rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, limit);
        }
        return mapper.personAgeGreatThan(params, rowBounds);
    }
}
