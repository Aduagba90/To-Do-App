package com.semicolon.africa.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    private String email;
}
