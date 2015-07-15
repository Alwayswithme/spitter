package me.phx.controler;

import me.phx.mapper.PersonMapper;
import me.phx.model.Person;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    PersonMapper personMapper;
    @Autowired
    private SqlSession sqlSession;

    @RequestMapping(value = "selectAll", method = RequestMethod.GET)
    public List<Person> selectAll() throws IOException {
        return personMapper.selectAll();
    }

    @RequestMapping(value = "selectById/{id}", method = RequestMethod.GET)
    public Person selectPersonWithDevices(@PathVariable int id) {
//        return mapper.selectPersonWithDevices(id);         // use annotation

        return personMapper.selectByPrimaryKey(id);  // use xml mapper
    }

//    @RequestMapping(value = "selectAndModifyById/{id}", method = RequestMethod.GET)
//    public Person selectAndModifyById(@PathVariable int id) {
//        Person p = personMapper.selectPersonJoinDevices(id);
//        System.out.println(p.getName());
//        p.setName("root2");
//        return p;
//    }

    @RequestMapping(value = "selectAllAsMap", method = RequestMethod.GET)
    public Map<Integer, Person> selectAllAsMap() throws IOException {
        return sqlSession.selectMap("me.phx.mapper.BaseMapper.selectAll", "name");

//        one-liner
//        return sqlSession.selectMap("PersonMapper.selectAll", "age");
    }

    /**
     * type-safe and more readable way
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Map<String,Object>> selectPersonAsMaps() throws IOException {
        return personMapper.selectPersonAsMaps();
    }
//
//    @RequestMapping(value = "count", method = RequestMethod.GET)
//    public int count() throws IOException {
//        return personMapper.count();
//    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(value = "insert", method = RequestMethod.POST, consumes = "application/json")
    public Person insert(@RequestBody Person person) throws IOException {
        personMapper.insert(person);

        return person;
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "deleteById/{id}", method = RequestMethod.GET)
    public int deleteOne(@PathVariable int id) throws IOException {
        Integer deleteCount = personMapper.deleteByPrimaryKey(id);

        // manually rollback
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return deleteCount;
    }

//    @RequestMapping(value = "selectByIds", method = RequestMethod.GET)
//    public List<Person> selectByIds(@RequestParam(value = "ids") String ids) throws IOException {
//        return personMapper.selectSpecificIds(ids);
//    }

//    @RequestMapping(value = "ageRange", method = RequestMethod.GET)
//    public List<Person> age(@RequestParam Map<String, Object> params,
//                                          @RequestParam(required = false) Integer limit,
//                                          @RequestParam(required = false) Integer offset) {
//        RowBounds rowBounds = RowBounds.DEFAULT;
//        if (params.containsKey("limit") && params.containsKey("offset")) {
//            rowBounds = new RowBounds(offset, limit);
//        } else if (params.containsKey("offset")) {
//            rowBounds = new RowBounds(offset, RowBounds.NO_ROW_LIMIT);
//        } else if (params.containsKey("limit")) {
//            rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, limit);
//        }
//        return personMapper.personAgeGreatThan(params, rowBounds);
//    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDataIntegrityException(DataIntegrityViolationException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.CONFLICT.getReasonPhrase());
        map.put("errMsg", e.getMessage());
        return map;
    }
}
