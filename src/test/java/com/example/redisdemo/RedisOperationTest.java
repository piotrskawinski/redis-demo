package com.example.redisdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RedisOperationTest {

	@Autowired
	private RedisOperationService redisOperation;

	@Test
	void testInsertGetValue () {
		redisOperation.add("user", "user_x");

		Assertions.assertEquals("user_x", redisOperation.get("user"));
	}

}
