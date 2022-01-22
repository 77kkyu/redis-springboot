package com.example.redisspringboot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCollectionTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void strings테스트() {

        final String key = "test";
        final ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();

        stringValueOperations.set(key, "1");
        final String getData = stringValueOperations.get(key);
        System.out.println("getData : " + getData);

    }

    @Test
    public void list테스트() {

        final String key = "test";
        final ListOperations<String, String> listOperations = redisTemplate.opsForList();

        listOperations.rightPush(key, "A");
        listOperations.rightPush(key, "B");
        listOperations.rightPush(key, "C");
        listOperations.rightPush(key, "D");
        listOperations.rightPush(key, "E");

        listOperations.rightPushAll(key, " ", "F", "G", "H", "I");
        final String getIndexData = listOperations.index(key, 1);
        System.out.println("getIndexData : " + getIndexData);

        final Long size = listOperations.size(key);
        System.out.println("size : " + size);

        final List<String> range = listOperations.range(key, 0, 9);
        System.out.println("range : " + Arrays.toString(range.toArray()));

    }

    @Test
    public void set테스트() {

        String key = "test";
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();

        setOperations.add(key, "A");
        setOperations.add(key, "B");
        setOperations.add(key, "C");
        setOperations.add(key, "D");
        setOperations.add(key, "E");

        Set<String> members = setOperations.members(key);
        System.out.println("mebers : " + Arrays.toString(members.toArray()));

        Long size = setOperations.size(key);
        System.out.println("size : " + size);

        Cursor<String> cursor = setOperations.scan(key, ScanOptions.scanOptions()
                        .match("*")
                        .count(3)
                        .build()
        );

        while (cursor.hasNext()) {
            System.out.println("cursor : " + cursor.next());
        }

    }

    @Test
    public void sortedSet테스트() {

        String key = "test";
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();

        zSetOperations.add(key, "A", 1);
        zSetOperations.add(key, "B", 5);
        zSetOperations.add(key, "C", 20);
        zSetOperations.add(key, "D", 15);
        zSetOperations.add(key, "E", 10);

        Set<String> range = zSetOperations.range(key, 0, 5);
        System.out.println("range : " + Arrays.toString(range.toArray()));

        Long size = zSetOperations.size(key);
        System.out.println("size : " + size);

        Set<String> score = zSetOperations.rangeByScore(key, 0, 10);
        System.out.println("score : " + Arrays.toString(score.toArray()));

    }

    @Test
    public void hash테스트() {

        String key = "test";
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        hashOperations.put(key, "A1", "test1");
        hashOperations.put(key, "A2", "test2");
        hashOperations.put(key, "A3", "test3");

        Object getData = hashOperations.get(key, "A2");
        System.out.println("getData : " + getData);

        Map<Object, Object> entries = hashOperations.entries(key);
        System.out.println("entries : " + entries.get("A2"));

    }

    @Test
    public void 레디스데이터삭제() {
        redisTemplate.delete("test");
    }

}
