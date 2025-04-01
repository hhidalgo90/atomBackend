package com.atom.tasksProject.util;

import com.atom.tasksProject.dto.TaskDTO;
import com.google.cloud.firestore.DocumentSnapshot;

public class TaskMapper {
    public static TaskDTO fromDocument(DocumentSnapshot doc) {
        TaskDTO task = doc.toObject(TaskDTO.class);
        assert task != null;
        task.setId(doc.getId());
        return task;
    }
}
