package com.springapp.mvc.repository;

import com.springapp.mvc.mapper.DeviceMapper;
import com.springapp.mvc.model.Device;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author phoenix
 */
@Component
public class SimpleDeviceRepository implements DeviceRepository {
    
    @Autowired
    private SqlSession sqlSession;

    
    @Override
    @Cacheable("device")
    public List<Device> getByOwnerId(Integer id) {
        simulateSlowService();
        return sqlSession.getMapper(DeviceMapper.class).selectDevicesByOwner(id);
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    } 

}
