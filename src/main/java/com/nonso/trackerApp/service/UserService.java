package com.nonso.trackerApp.service;

import com.nonso.trackerApp.dto.user.LoginDto;
import com.nonso.trackerApp.dto.user.RegistrationDto;
import com.nonso.trackerApp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(RegistrationDto registrationDto);

    boolean loginUser(LoginDto loginDto);

    User findById(Long id);
}
