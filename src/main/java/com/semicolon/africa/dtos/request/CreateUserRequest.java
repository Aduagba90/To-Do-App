package com.semicolon.africa.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}
