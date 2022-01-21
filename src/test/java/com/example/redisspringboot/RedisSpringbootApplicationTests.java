package com.example.redisspringboot;

import com.example.redisspringboot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisSpringbootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void 레디스연결테스트() {
        final String key = "test";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        final String getValue = valueOperations.get(key);
        assertEquals(data, getValue);
    }

    @Test
    public void 레디스데이터확인() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String getData = valueOperations.get("test");
        System.out.println("getData : " + getData);
    }

    @Test
    void 레디스삭제테스트() {
        redisTemplate.delete("test");
    }

    @Test
    public void 레디스객체테스트() {
        User user = User.builder()
                .id("77kkyu")
                .password("123")
                .build();

        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(user.getId(), user);

        User getUser = valueOperations.get(user.getId());
        System.out.println("getUser : " + getUser);
    }

}