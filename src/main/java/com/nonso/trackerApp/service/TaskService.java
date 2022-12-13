package com.nonso.trackerApp.service;

import com.nonso.trackerApp.dto.task.TaskDto;
import com.nonso.trackerApp.model.Status;
import com.nonso.trackerApp.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> viewAllTasks();
    Task addTask(TaskDto taskDto);
    Task viewById(Long id);


//    List<Task> getAllTasksCategoryById(Long id);

    List<Task> findTaskByStatus(Status status);
    Task updateTask(Task task);
    void deleteTask(Task task);

}
