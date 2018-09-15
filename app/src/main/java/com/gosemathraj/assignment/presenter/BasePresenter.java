package com.gosemathraj.assignment.presenter;

import com.gosemathraj.assignment.models.User;

import java.util.List;

/**
 * Created by iamsparsh on 14/9/18.
 */

public interface BasePresenter {
    void loadUsers();

    void insertAllUsers(List<User> userList);

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
