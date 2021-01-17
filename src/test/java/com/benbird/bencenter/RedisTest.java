package com.benbird.bencenter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 项目名: bencenter
 * 创建者: Admin
 * 创建时间: 2021/1/15
 * 描述:
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void set() {
        redisTemplate.opsForValue().set("test", "test", 1000, TimeUnit.SECONDS);
    }
}
