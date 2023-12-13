package top.jocularchao;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class TemplateBackendApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //$2a$10$RQ4LuWr5hL73VDxztxJGu.lAwCTHustBif5sKiKK9866u.1m1LUaS
        System.out.println(encoder.encode("123456"));
    }

}
