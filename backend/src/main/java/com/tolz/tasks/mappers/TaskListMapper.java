package com.tolz.tasks.mappers;

import com.tolz.tasks.domain.entities.TaskList;
import com.tolz.tasks.domain.entities.dto.TaskListDto;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);

    //// takes a task and returns a dto
    TaskListDto toDto(TaskList taskList);
}
