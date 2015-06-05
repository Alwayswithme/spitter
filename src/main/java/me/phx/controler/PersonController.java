package me.phx.controler;

import me.phx.mybatis.mapper.PersonMapper;
import me.phx.model.Person;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author phoenix
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value = "selectAll", method = RequestMethod.GET, produces = "application/json")
    public List<Person> selectAll() throws IOException {
        return sqlSession.selectList("PersonMapper.selectAll");
    }

    @RequestMapping(value = "selectById/{id}", method = RequestMethod.GET)
    public Person selectPersonWithDevices(@PathVariable int id) {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
//        return mapper.selectPersonWithDevices(id);         // use annotation

        return mapper.selectPersonJoinDevices(id);  // use xml mapper
    }

    @RequestMapping(value = "selectAllAsMap", method = RequestMethod.GET)
    public Map<Integer, Person> selectAllAsMap() throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        return mapper.selectPersonAsMapById();

//        one-liner
//        return sqlSession.selectMap("PersonMapper.selectAll", "age");
    }

    /**
     * type-safe and more readable way
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
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
    @RequestMapping(value = "insert", method = RequestMethod.POST, consumes = "application/json")
    public Person insert(@RequestBody Person person) throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        mapper.insertPerson(person);

        return person;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(value = "deleteById/{id}", method = RequestMethod.GET)
    public int deleteOne(@PathVariable int id) throws IOException {
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        mapper.deleteById(id);

        // manually rollback
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return mapper.deleteById(id);
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
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDataIntegrityException(DataIntegrityViolationException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.CONFLICT.getReasonPhrase());
        map.put("errMsg", e.getMessage());
        return map;
    }
}
