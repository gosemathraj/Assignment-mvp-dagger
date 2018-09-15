package com.gosemathraj.assignment.view;

import com.gosemathraj.assignment.models.User;

import java.util.List;

/**
 * Created by iamsparsh on 14/9/18.
 */

public interface BaseView {

    void showProgressbar();

    void closeProgressbar();

    void showusers(List<User> userList);

    void showError(String message);

    void updateList();
}
