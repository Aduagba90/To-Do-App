package com.semicolon.africa.dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DeleteTaskRequest {
    private String taskId;
}
