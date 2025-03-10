package com.semicolon.africa.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AddTaskRequest {
    private String title;
    private String description;
    private String userEmail;
}
