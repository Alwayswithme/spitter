package me.phx.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


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

    @RequestMapping("run3")
    public void run3() {
        String key = "connections";

        System.out.println(valueOperations.setIfAbsent(key, String.valueOf(10)));
        System.out.println(valueOperations.increment(key, 1));
        System.out.println(valueOperations.increment(key, 1));

        stringRedisTemplate.delete(key);

        System.out.println(valueOperations.increment(key, 1));
    }

    @RequestMapping("run4")
    public void run4() throws InterruptedException {
        String key = "resource:lock";

        System.out.println(valueOperations.setIfAbsent(key, "Redis Demo"));
        System.out.println(stringRedisTemplate.expire(key, 10, TimeUnit.SECONDS));
        System.out.println(stringRedisTemplate.getExpire(key));

        valueOperations.set(key, "Redis Demo 1");
        System.out.println(stringRedisTemplate.expire(key, 10, TimeUnit.SECONDS));
        TimeUnit.SECONDS.sleep(2);

        System.out.println(stringRedisTemplate.getExpire(key));

        valueOperations.set(key, "Redis Demo 2");
        System.out.println(stringRedisTemplate.getExpire(key));
    }

    @RequestMapping("run5")
    public void run5() throws InterruptedException {
        String key = "friends";
        ListOperations<String, String> opsForList = stringRedisTemplate.opsForList();

        System.out.println(opsForList.rightPush(key, "Alice"));
        System.out.println(opsForList.rightPush(key, "Bob"));
        System.out.println(opsForList.leftPush(key, "Sam"));

        System.out.println(opsForList.size(key));

        System.out.println(opsForList.range(key, 0, -1));
        System.out.println(opsForList.range(key, 0, 1));
        System.out.println(opsForList.range(key, 1, 2));

        System.out.println(opsForList.leftPop(key));
        System.out.println(opsForList.rightPop(key));
        System.out.println(opsForList.range(key, 0, -1));
    }

    @RequestMapping("run6")
    public void run6() throws InterruptedException {
        String key = "superpowers";
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();

        System.out.println(opsForSet.add(key, "flight"));
        System.out.println(opsForSet.add(key, "x-ray vision"));
        System.out.println(opsForSet.add(key, "reflexes"));
        System.out.println(opsForSet.remove(key, "reflexes"));
        System.out.println(opsForSet.isMember(key, "flight"));
        System.out.println(opsForSet.isMember(key, "reflexes"));
        System.out.println(opsForSet.members(key));

        String key2 = "birdpowers";
        System.out.println(opsForSet.add(key2, "flight"));
        System.out.println(opsForSet.add(key2, "pecking"));
        System.out.println(opsForSet.union(key, key2));
    }

    @RequestMapping("run7")
    public void run7() throws InterruptedException {
        String key = "hackers";
        ZSetOperations<String, String> opsForZSet = stringRedisTemplate.opsForZSet();

        System.out.println(opsForZSet.add(key, "Alan Kay", 1940));
        System.out.println(opsForZSet.add(key, "Grace Hopper", 1906));
        System.out.println(opsForZSet.add(key, "Richard Stallman", 1953));
        System.out.println(opsForZSet.add(key, "Yukihiro Matsumoto", 1965));
        System.out.println(opsForZSet.add(key, "Claude Shannon", 1916));
        System.out.println(opsForZSet.add(key, "Linus Torvalds", 1969));
        System.out.println(opsForZSet.add(key, "Sophie Wilson", 1957));
        System.out.println(opsForZSet.add(key, "Alan Turing", 1912));
        System.out.println(opsForZSet.range(key, 2, 4));
    }
}
