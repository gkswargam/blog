package com.gkswargam.blog.users;

import com.gkswargam.blog.users.dtos.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity createUser(CreateUserRequest request) {
        var newUser = UserEntity.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .build();
        return usersRepository.save(newUser);
    }

    public UserEntity getUser(String userName) {
        return usersRepository.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(userName));
    }

    public UserEntity getUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String username) {
        return usersRepository.findByUserName(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public static class UserNotFoundException extends IllegalArgumentException {
        public UserNotFoundException(String username) {
            super("User with username:" + username + " not found ");
        }
        public UserNotFoundException(Long userId) {
            super("User with id:" + userId + " not found ");
        }
    }
}
