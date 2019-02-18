package com.example.redisdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@SpringBootApplication
@Slf4j
public class RedisDemoApplication {

    private static final int PORT = 6379;
    private RedisServer redisServer;

    public static void main (String[] args) {
        SpringApplication.run(RedisDemoApplication.class, args);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory () {
        return new LettuceConnectionFactory("localhost", PORT);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate () {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public ValueOperations<String, String> valueOperations () {
        return redisTemplate().opsForValue();
    }

    /*@Bean
    public CommandLineRunner commandLineRunner () {
        return args -> {
            ValueOperations<String, String> valueOperations = valueOperations();

            valueOperations.set("user", "user_x");

            String user = valueOperations.get("user");
            log.info("User -> {}", user);
        };
    }*/

    @PostConstruct
    public void startRedis () throws IOException {
        redisServer = RedisServer.builder()
                .setting("bind 127.0.0.1")
                .port(PORT).build();

        redisServer.start();

        log.info("Redis server started at port {}", PORT);
    }

    @PreDestroy
    public void stopRedis () {
        redisServer.stop();

        log.info("Redis server stopped");
    }

}
