package com.nonso.trackerApp.controller;

import com.nonso.trackerApp.dto.user.LoginDto;
import com.nonso.trackerApp.dto.user.RegistrationDto;
import com.nonso.trackerApp.service.TaskService;
import com.nonso.trackerApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    UserService userService;
    TaskService taskService;

    @Autowired
    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showDashBoard(Model model) {
        model.addAttribute("tasks", taskService.viewAllTasks());
        return "index";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return "register-form";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegistrationDto registrationDto) {
        userService.createUser(registrationDto);
            return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String Login(@ModelAttribute("user") LoginDto loginDto, HttpSession session) {

        session.setAttribute("user", loginDto.getEmail());
      if (userService.loginUser(loginDto)) {
          return "redirect:/";

      }
        return "redirect:/error";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/update-user/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        model.addAttribute("update", userService.findById(id));
        return "updateTask";
    }
}
