package com.tolz.tasks.services.impl;

import com.tolz.tasks.domain.entities.Task;
import com.tolz.tasks.domain.entities.TaskList;
import com.tolz.tasks.domain.entities.TaskPriority;
import com.tolz.tasks.domain.entities.TaskStatus;
import com.tolz.tasks.domain.entities.dto.TaskDto;
import com.tolz.tasks.mappers.TaskMapper;
import com.tolz.tasks.mappers.impl.TaskMapperImpl;
import com.tolz.tasks.repositories.TaskListRepository;
import com.tolz.tasks.repositories.TaskRepository;
import com.tolz.tasks.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()) {
            throw new IllegalArgumentException("Task already has an ID");
        }

        if ( null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }

        TaskPriority taskPriority = Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPENED;
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() ->
               new IllegalArgumentException("Invalid Task List ID provided!"));

        LocalDateTime now = LocalDateTime.now();

        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId()) {
            throw new IllegalArgumentException("Task ID cannot be empty");
        }

        if (!Objects.equals(task.getId(), taskId)) {
            throw new IllegalArgumentException("Task ID does not match");
        }

        if ( null == task.getTitle() || task.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty");
        }
        if ( null == task.getPriority()) {
            throw new IllegalArgumentException("Task priority cannot be empty");
        }
        if (null != task.getStatus()) {
            throw new IllegalArgumentException("Task status cannot be empty");
        }

        Task exsitingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(() ->
                new IllegalArgumentException("Invalid Task List ID provided!"));

        LocalDateTime now = LocalDateTime.now();

       exsitingTask.setTitle(task.getTitle());
       exsitingTask.setDescription(task.getDescription());
       exsitingTask.setDueDate(task.getDueDate());
       exsitingTask.setStatus(task.getStatus());
       exsitingTask.setPriority(task.getPriority());
       exsitingTask.setUpdated(now);

        return taskRepository.save(exsitingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId,taskId);
    }
}
