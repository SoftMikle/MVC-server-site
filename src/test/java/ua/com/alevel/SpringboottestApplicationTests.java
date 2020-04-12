package ua.com.alevel;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.entity.User;
import ua.com.alevel.servive.UserService;

@SpringBootTest
class SpringboottestApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setEmail("yesterday@gmail.com");
        user.setUserName("Developer");
        userService.create(user);
    }

}
