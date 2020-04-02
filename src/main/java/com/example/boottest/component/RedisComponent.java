package com.example.boottest.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

@Component
public class RedisComponent {
    @Resource(name = "selfRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    public void add(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Long rank(String key, String value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    public Double score(String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    public Set<String> range(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<String>> rangeWithScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    public void hashPut(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    public void hashPutAll(String key, Map<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public Map<Object, Object> hget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
}
