package me.phx.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author ye
 */

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valueOperations;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisAtomicInteger redisAtomicInteger;

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

        System.out.println(redisAtomicInteger.get());
        redisAtomicInteger.set(10);
        System.out.println(redisAtomicInteger.get());

        System.out.println(redisAtomicInteger.incrementAndGet());
        System.out.println(redisAtomicInteger.getAndIncrement());


        System.out.println(redisAtomicInteger.getAndSet(0));
        System.out.println(redisAtomicInteger.get());
    }
}
