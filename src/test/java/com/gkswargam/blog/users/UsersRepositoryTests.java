package com.gkswargam.blog.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.beans.Transient;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTests {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void can_create_users() {
        UserEntity userEntity = UserEntity.builder()
                .username("test-user-1")
                .email("test-user-1@email.com")
                .build();
        usersRepository.save(userEntity);
    }

    @Test
    void can_find_all_users() {
        UserEntity userEntity = UserEntity.builder()
                .username("test-user-1")
                .email("test-user-1@email.com")
                .build();
        usersRepository.save(userEntity);

        var users = usersRepository.findAll();
        Assertions.assertEquals(1, users.size());
    }
}
