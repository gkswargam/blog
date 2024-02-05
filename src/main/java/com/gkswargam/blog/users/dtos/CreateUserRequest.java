package com.gkswargam.blog.users.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserRequest {
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String email;
}
