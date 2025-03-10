package com.semicolon.africa.dtos.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AddTaskResponse {
    private String id;
    private String userEmail;
    private String message;
}
