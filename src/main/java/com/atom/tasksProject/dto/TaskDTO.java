package com.atom.tasksProject.dto;

import lombok.*;

@Data
@Getter
@Setter
public class TaskDTO {
    private String id;

    private String title;
    private String description;
    private boolean completed;

    private String createdAt;
}
