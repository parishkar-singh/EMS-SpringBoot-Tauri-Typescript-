package org.api.api.service;

import org.api.api.model.Role;
import org.api.api.model.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String user, String role);

    User getUser(String username);

    List<User> getUser();
}
