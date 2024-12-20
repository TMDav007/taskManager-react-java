package com.tolz.tasks.mappers;

import com.tolz.tasks.domain.entities.Task;
import com.tolz.tasks.domain.entities.dto.TaskDto;

public interface TaskMapper {

    //// takes a dtos and returns a task
    Task fromDto(TaskDto taskDto);

    //// takes a task and returns a dto
    TaskDto toDto(Task task);
}
