package com.kunge2013.spring.transaction;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/5/24 20:02
 * @Version 1.0
 */
import com.kunge2013.spring.transaction.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TransactionTest {

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void clean() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS tb_user");
        jdbcTemplate.execute("CREATE TABLE tb_user (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) NOT NULL," +
                "age INT NOT NULL)");
    }

    @Test
    void testNonPublicMethod() {
        assertThrows(Exception.class, () -> userService.testPrivateMethod());
        assertRecordExists("private");
    }

    @Test
    void testSelfInvocation() {
        userService.selfInvocation();
        assertRecordExists("self");
    }

    @Test
    void testCheckedException() throws Exception {
        assertThrows(Exception.class, () -> userService.checkedException());
        assertRecordExists("checked");
    }

    @Test
    void testSwallowException() {
        userService.swallowException();
        assertRecordExists("swallow");
    }

    @Test
    void testPropagationNotSupported() {
        userService.notSupported();
        assertRecordExists("notsupported");
    }

    private void assertRecordExists(String username) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM tb_user WHERE username = ?",
                Integer.class,
                username
        );
        assertThat(count).isEqualTo(1);
    }
}
