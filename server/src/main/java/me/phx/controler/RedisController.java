package me.phx.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author ye
 */

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<String, String> valueOperations;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/run1")
    public void run1() {
        String key = "server:name";

        String s = valueOperations.get(key);
        System.out.println(s);

        valueOperations.set(key, "fido");

        String s1 = valueOperations.get(key);
        System.out.println(s1);
    }

    @RequestMapping(value = "/run2")
    public void run2() {
        String key = "connections";

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "10");
        System.out.println(valueOperations.get(key));
        if (redisTemplate.hasKey(key)) {
            System.out.println(valueOperations.increment(key, 1));
            System.out.println(valueOperations.increment(key, 1));

            redisTemplate.delete(key);

            System.out.println(valueOperations.increment(key, 1));
        }
    }
}
