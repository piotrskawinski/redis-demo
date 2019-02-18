package com.example.redisdemo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RedisOperationService {
    private ValueOperations<String, String> valueOperations;

    public void add (String key, String value) {
        valueOperations.set(key, value);
    }

    public String get (String key) {
        return valueOperations.get(key);
    }
}
