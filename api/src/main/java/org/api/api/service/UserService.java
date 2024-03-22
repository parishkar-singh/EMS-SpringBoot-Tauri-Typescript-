package org.api.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.api.api.model.Role;
import org.api.api.model.User;
import org.api.api.repository.RoleRepository;
import org.api.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements IUserService {
    org.api.api.utils.Logger UserServiceLogger=new org.api.api.utils.Logger ("User Service");
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        try {
            UserServiceLogger.logDatabase("User Saved");
            return userRepository.save(user);
        } catch (Exception e) {
            UserServiceLogger.logError("Error saving user: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Role saveRole(Role role) {
        try {
            UserServiceLogger.logDatabase("Role Saved");
            return roleRepository.save(role);
        } catch (Exception e) {
            UserServiceLogger.logError("Error saving role: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);
        if (user != null && role != null) {
            user.getRoles().add(role);
            UserServiceLogger.logDatabase("Role " + roleName + " added to User " + userName);
        } else {
            UserServiceLogger.logError("User or Role not found");
        }
    }

    @Override
    public User getUser(String userName) {
        try {
            return userRepository.findByUsername(userName);
        } catch (Exception e) {
            UserServiceLogger.logError("Error getting user: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getUser() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            UserServiceLogger.logError("Error getting users: " + e.getMessage());
            throw e; 
        }
    }
}