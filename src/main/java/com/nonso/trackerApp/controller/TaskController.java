package com.nonso.trackerApp.controller;

import com.nonso.trackerApp.dto.task.TaskDto;
import com.nonso.trackerApp.model.Status;
import com.nonso.trackerApp.model.Task;
import com.nonso.trackerApp.service.TaskService;
import com.nonso.trackerApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class TaskController {

    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;

    }

    @GetMapping("/tasks")
    public String showTasks(Model model) {
        model.addAttribute("tasks", taskService.viewAllTasks());
        return "/";
    }

//
//    @GetMapping("task/categories")
//    public String getTaskCategories(Model model) {
//
//    }

    @GetMapping("/task/create")
    public String showTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "task-form";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute("task")TaskDto taskDto) {
        taskService.addTask(taskDto);
        return "redirect:/"; //add dashboard form here, remove dashboard as part in user
    }

    @GetMapping("/edit/{id}")
    public String editTaskForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.viewById(id));
        return "updateTask";
    }

    @PostMapping("update-task/{id}")
    public String editTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.viewById(id);
        task.setUpdatedAt(LocalDateTime.now());
        task.setId(id);
        taskService.updateTask(task);
        model.addAttribute("tasks", taskService.viewById(id));
        return "redirect:/"; // still add the dashboard here
    }

    @GetMapping("delete-task/{id}")
    public String deleteTask(@PathVariable("id") Long id, Model model) {
        Task task = taskService.viewById(id);
        model.addAttribute("task", task);

        taskService.deleteTask(task);
        return "redirect:/"; // dashboard
    }

    @GetMapping("move-up/{id}")
    public String moveUp(@PathVariable("id") Long id, Model model){
        Task task = taskService.viewById(id);
        if(task.getStatus() == Status.PENDING && task.getStatus() != Status.DONE){
            task.setStatus(Status.IN_PROGRESS);
            taskService.updateTask(task);
            return "redirect:/";
        }
        else if(task.getStatus() == Status.IN_PROGRESS && task.getStatus() != Status.PENDING) {
            task.setCompletedAt(LocalDateTime.now());
            task.setStatus(Status.DONE);
            taskService.updateTask(task);
            return "redirect:/";
        }
        else{
            return "redirect:/";
        }
    }

    @GetMapping("move-down/{id}")
    public String moveDown(@PathVariable("id") Long id, Model model){
        Task task = taskService.viewById(id);
        if(task.getStatus() == Status.DONE){
            task.setCompletedAt(null);
            task.setStatus(Status.IN_PROGRESS);
            taskService.updateTask(task);
            return "redirect:/";
        }

        else if(task.getStatus() == Status.IN_PROGRESS){
            task.setStatus(Status.PENDING);
            taskService.updateTask(task);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("pending")
    public String viewPendingTasks(Model model){

        List<Task> pendingTasks = new ArrayList<>();
        List<Task> allTasks = taskService.viewAllTasks();
        for(Task task: allTasks){
            if(task.getStatus() == Status.PENDING){
                pendingTasks.add(task);
            }
        }
        model.addAttribute("pendingTasks", pendingTasks);

        return "taskByPending";

    }

    @GetMapping("in-progress")
    public String viewTasksInProgress(Model model){

        List<Task> progress = new ArrayList<>();
        List<Task> allTasks = taskService.viewAllTasks();
        for(Task task: allTasks){
            if(task.getStatus() == Status.IN_PROGRESS){
                progress.add(task);
            }
        }
        model.addAttribute("progress", progress);

        return "in-progress";

    }

    @GetMapping("done-tasks")
    public String viewDoneTasks(Model model){

        List<Task> doneTasks = new ArrayList<>();
        List<Task> allTasks = taskService.viewAllTasks();
        for(Task task: allTasks){
            if(task.getStatus() == Status.DONE){
                doneTasks.add(task);
            }
        }
        model.addAttribute("done", doneTasks);

        return "done-tasks";

    }

}
