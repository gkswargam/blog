package com.gkswargam.blog.users;

import com.gkswargam.blog.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Test
    void can_create_users() {
        var user = userService.createUser(new CreateUserRequest(
                "test-user-2",
                "xxxxxxxx",
                "test-user-2@email.com"
                ));

        Assertions.assertNotNull(user);
        Assertions.assertEquals("test-user-2", user.getUserName());
        Assertions.assertEquals("test-user-2@email.com", user.getEmail());
    }
}
