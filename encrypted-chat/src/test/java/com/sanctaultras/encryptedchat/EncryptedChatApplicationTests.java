package com.sanctaultras.encryptedchat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.autoconfigure.exclude=" +
				"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class")
public class EncryptedChatApplicationTests {

	@Test
	public void contextLoads() {
	}

}
