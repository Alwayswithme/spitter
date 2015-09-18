package me.phx.service;

import com.google.common.collect.ImmutableBiMap;
import me.phx.config.RedisConfig;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author ye
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = {SimpleRedisApplication.class, RedisConfig.class})
public class RedisTemplateTest {

    @Value("${test.redis.key1}") String key1;
    @Value("${test.redis.key2}") String key2;
    @Value("${test.redis.key3}") String key3;
    @Value("${test.redis.key4}") String key4;
    @Value("${test.redis.key5}") String key5;

    @Value("${test.redis.aSet}") String set1;
    @Value("${test.redis.bSet}") String set2;


    @Value("${test.redis.hash.key0}") String hkey0;
    @Value("${test.redis.hash.key1}") String hkey1;

    @Autowired
    RedisAtomicInteger redisAtomicInteger;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valueOperations;
    @Resource(name = "stringRedisTemplate")
    ListOperations<String, String> listOperations;
    @Resource(name = "stringRedisTemplate")
    SetOperations<String, String> setOperations;
    @Resource(name = "stringRedisTemplate")
    ZSetOperations<String, String> zSetOperations;
    @Resource(name = "stringRedisTemplate")
    HashOperations<String, Object, Object> hashOperations;
    @After
    public void cleanUp() {
        List<String> keyList = Arrays.asList(key1, key2, key3, key4, key5, set1, set2, hkey0, hkey1);
        for (String s : keyList) {
            stringRedisTemplate.delete(s);
        }
    }

    @Test
    public void testGetSet() {
        String value = "fido";
        String s = valueOperations.get(key1);

        assertThat(s, is(nullValue()));

        stringRedisTemplate.delete(key1);

        assertTrue(valueOperations.setIfAbsent(key1, value));
        assertThat(valueOperations.get(key1), equalTo(value));
    }

    @Test
    public void testCounter() {
        redisAtomicInteger.set(10);
        assertThat(redisAtomicInteger.get(), is(10));
        assertThat(redisAtomicInteger.incrementAndGet(), is(11));
        assertThat(redisAtomicInteger.getAndIncrement(), is(11));

        assertThat(redisAtomicInteger.getAndSet(0), is(12));
        assertThat(redisAtomicInteger.get(), is(0));
    }

    @Test
    public void testIncrement() {
        assertTrue(valueOperations.setIfAbsent(key2, String.valueOf(10)));
        assertThat(valueOperations.increment(key2, 1), is(11L));
        assertThat(valueOperations.increment(key2, 9), is(20L));

        stringRedisTemplate.delete(key2);

        assertThat(valueOperations.increment(key2, 1), is(1L));
    }

    @Test
    public void testTTL() throws InterruptedException {
        assertTrue(valueOperations.setIfAbsent(key3, "Redis Demo"));
        assertTrue(stringRedisTemplate.expire(key3, 10, TimeUnit.SECONDS));
        assertThat(stringRedisTemplate.getExpire(key3), is(greaterThan(0L)));

        valueOperations.set(key3, "Redis Demo 1");
        assertTrue(stringRedisTemplate.expire(key3, 10, TimeUnit.SECONDS));
        TimeUnit.SECONDS.sleep(2);
        assertThat(stringRedisTemplate.getExpire(key3), is(lessThan(10L)));

        valueOperations.set(key3, "Redis Demo 2");
        assertThat(stringRedisTemplate.getExpire(key3), is(lessThan(0L)));
    }

    @Test
    public void testListOps() throws InterruptedException {
        assertThat(listOperations.rightPush(key4, "Alice"), is(equalTo(1L)));
        assertThat(listOperations.rightPush(key4, "Bob"), is(equalTo(2L)));
        assertThat(listOperations.leftPush(key4, "Sam"), is(equalTo(3L)));


        assertThat(listOperations.size(key4), is(3L));

        assertThat(listOperations.range(key4, 0, -1), contains("Sam", "Alice", "Bob"));
        assertThat(listOperations.range(key4, 0, 1), contains("Sam", "Alice"));
        assertThat(listOperations.range(key4, 1, 2), contains("Alice", "Bob"));

        assertThat(listOperations.leftPop(key4), is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("Sam"))));

        assertThat(listOperations.rightPop(key4), is(
                allOf(notNullValue(), instanceOf(String.class), equalTo("Bob"))));

        List<String> remaining = listOperations.range(key4, 0, -1);
        assertThat(remaining, hasSize(1));
        assertThat(remaining, hasItem("Alice"));
    }

    @Test
    public void testSetOps() throws InterruptedException {
        assertThat(setOperations.add(set1, "flight"), equalTo(1L));
        assertThat(setOperations.add(set1, "flight"), equalTo(0L));
        assertThat(setOperations.add(set1, "x-ray vision"), is(1L));
        assertThat(setOperations.add(set1, "reflexes"), is(1L));

        assertThat(setOperations.remove(set1, "reflexes"), is(1L));

        assertThat(setOperations.isMember(set1, "flight"), is(true));
        assertThat(setOperations.isMember(set1, "reflexes"), is(false));
        assertThat(setOperations.members(set1), hasItems("x-ray vision", "flight"));

        assertThat(setOperations.add(set2, "flight"), is(1L));
        assertThat(setOperations.add(set2, "pecking"), is(1L));
        Set<String> union = setOperations.union(set1, set2);
        assertThat(union, hasSize(3));
        assertThat(union, hasItems("flight", "pecking", "x-ray vision"));
    }

    @Test
    public void testZsetOps() throws InterruptedException {
        Set<ZSetOperations.TypedTuple<String>> zSet = new HashSet<>();

        zSet.add(new DefaultTypedTuple<>("Alan Kay", (double) 1940));
        zSet.add(new DefaultTypedTuple<>("Grace Hopper", (double) 1906));
        zSet.add(new DefaultTypedTuple<>("Richard Stallman", (double) 1953));
        zSet.add(new DefaultTypedTuple<>("Yukihiro Matsumoto", (double) 1965));

        assertThat(zSetOperations.add(key5, zSet), is(4L));

        assertThat(zSetOperations.add(key5, "Claude Shannon", 1916), is(Boolean.TRUE));
        assertThat(zSetOperations.add(key5, "Claude Shannon", 1916), is(Boolean.FALSE));
        assertThat(zSetOperations.add(key5, "Linus Torvalds", 1969), is(Boolean.TRUE));
        assertThat(zSetOperations.add(key5, "Sophie Wilson", 1957), is(Boolean.TRUE));
        assertThat(zSetOperations.add(key5, "Alan Turing", 1912), is(Boolean.TRUE));
        Set<String> range = zSetOperations.range(key5, 2, 4);
        assertThat(range, hasSize(3));
        assertThat(range, hasItems("Richard Stallman"));
    }

    @Test
    public void testHashOps() throws InterruptedException {
        hashOperations.put(hkey0, "name", "John Smith");
        hashOperations.put(hkey0, "email", "john.smith@example.com");
        hashOperations.put(hkey0, "password", "s3cret");
        assertThat(hashOperations.entries(hkey0), allOf(hasEntry("name", "John Smith"), hasEntry("password", "s3cret")));

        hashOperations.putAll(hkey1,
                ImmutableBiMap.of("name", "Mary Jones",
                        "password", "hidden",
                        "email", "mjones@example.com"));
        assertThat(hashOperations.get(hkey1, "name"), is("Mary Jones"));

        String visits = "visits";
        assertThat(hashOperations.putIfAbsent(hkey0, visits, "10"), is(true));
        assertThat(hashOperations.putIfAbsent(hkey0, visits, "100"), is(false));
        assertThat(hashOperations.increment(hkey0, visits, 1), is(11L));
        assertThat(hashOperations.increment(hkey0, visits, 10), is(21L));

        hashOperations.delete(hkey0, visits);
        assertThat(hashOperations.get(hkey0, visits), is(nullValue()));
        assertThat(hashOperations.increment(hkey0, visits, 1), is(1L));
        assertThat(hashOperations.hasKey(hkey0, visits), is(true));
    }
}
