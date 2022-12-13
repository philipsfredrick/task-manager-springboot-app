package com.nonso.trackerApp.service.impl;

import com.nonso.trackerApp.dto.user.LoginDto;
import com.nonso.trackerApp.dto.user.RegistrationDto;
import com.nonso.trackerApp.model.User;
import com.nonso.trackerApp.repository.UserRepository;
import com.nonso.trackerApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());

        return userRepository.save(user);
    }

    @Override
    public boolean loginUser(LoginDto loginDto) {
       return userRepository.findByEmailAAndPassword(loginDto.getEmail(), loginDto.getPassword()) != null;
    }

    @Override
    public User findById(Long id) {
        User user = userRepository.findById(id).orElse(null);

        return user;
    }
}
