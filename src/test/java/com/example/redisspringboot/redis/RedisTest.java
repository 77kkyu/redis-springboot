package com.example.redisspringboot.redis;

import com.example.redisspringboot.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void 레디스데이터저장() {
        String key = "test";
        String data = "1";

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        String getValue = valueOperations.get(key);
        assertEquals(data, getValue);
    }

    @Test
    public void 레디스데이터삭제() {
        redisTemplate.delete("test");
    }

    @Test
    public void 레디스setOperations테스트() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        String key = "key";

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        int index = 0;
        for (String k : list) {
            System.out.println("k : " + k);
            setOperations.add(key, list.get(index));
            index++;
        }

        assertEquals(true, checkAllInside(key, setOperations, list));

        for (String s: setOperations.members(key)) {
            System.out.println("s : " + s);
        }

    }

    private boolean checkAllInside(String setKey, SetOperations<String, String> setOperations, List<String> list) {
        boolean flag = true;
        for (String o : list) {
            System.out.println(o);
            if (!setOperations.isMember(setKey, o)) {
                flag = false;
            }
        }
        return flag;
    }

    @Test
    public void 레디스sortedSet테스트() {
        String key = "user";

        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }

        ZSetOperations<String, User> zSetOperations = redisTemplate.opsForZSet();
        List<User> list = new ArrayList<>();
        for (int i=0; i<100; i++) {
            User user = User.builder()
                    .id(key+i)
                    .level(i)
                    .build();
            list.add(user);
            zSetOperations.add(key, user, user.getLevel());
        }
        Set<User> set = zSetOperations.range(key, 0, 100);
        assertEquals(list.size(), set.size());
    }

}
