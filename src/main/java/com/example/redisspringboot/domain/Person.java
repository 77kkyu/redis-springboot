package com.example.redisspringboot.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("people")
public class Person {

    @Id
    private String id;

    private String name;

    private String email;

}
