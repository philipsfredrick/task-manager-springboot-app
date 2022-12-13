package com.nonso.trackerApp.service.impl;

import com.nonso.trackerApp.dto.task.TaskDto;
import com.nonso.trackerApp.exceptions.TaskNotFoundException;
import com.nonso.trackerApp.model.Status;
import com.nonso.trackerApp.model.Task;
import com.nonso.trackerApp.repository.TaskRepository;
import com.nonso.trackerApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> viewAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(Status.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public Task viewById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("task was not found"));
    }

//    @Override
//    public List<Task> getAllTasksCategoryById(Long id) {
//        return taskRepository.findAllByCategory_Id(id);
//    }

    @Override
    public Task updateTask(Task task) {
       return taskRepository.save(task);
    }

    @Override
    public List<Task> findTaskByStatus(Status status) {
        return taskRepository.findTasksByStatus(status);

    }

    @Override
    public void deleteTask(Task task) {
       taskRepository.delete(task);
    }
}
