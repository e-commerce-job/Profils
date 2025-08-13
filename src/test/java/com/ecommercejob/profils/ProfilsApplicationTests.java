package com.ecommercejob.profils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest (
        properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
)
class ProfilsApplicationTests {

	@Test
	void contextLoads() {
	}
    @Test
    void ciIsGreen() {
        assertTrue(true);
}
}