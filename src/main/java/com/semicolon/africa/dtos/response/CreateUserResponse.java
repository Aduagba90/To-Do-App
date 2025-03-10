package com.semicolon.africa.dtos.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class CreateUserResponse {
    private String message;
    private String email;
    private String id;
}
