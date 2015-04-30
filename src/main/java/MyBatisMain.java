import com.springapp.mvc.mapper.DeviceMapper;
import com.springapp.mvc.mapper.PersonMapper;
import com.springapp.mvc.model.Device;
import com.springapp.mvc.model.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author phoenix
 */
public class MyBatisMain {
    public static void main(String[] args) throws IOException {
        String resources = "mybatis.xml";
        Reader reader = Resources.getResourceAsReader(resources);
        SqlSessionFactory ssFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession sqlSession = ssFactory.openSession()) {
            DeviceMapper mapper = sqlSession.getMapper(DeviceMapper.class);
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
            
            Person p = personMapper.selectOne(2);
            List<Device> l = mapper.selectDevicesByIds(1);
            Device d = l.get(0);
            d.setName("iPhone 6");
            mapper.updateDevice(d);
            personMapper.deleteById(6);
            sqlSession.commit();
        }
    }
}
