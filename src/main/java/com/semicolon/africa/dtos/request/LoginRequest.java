package com.semicolon.africa.dtos.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class LoginRequest {
    private String email;
    private String password;
}
