package com.moneysplit.api.service;

import java.util.List;

import com.moneysplit.api.dao.UserDAO;
import com.moneysplit.api.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public void createUser(final User user) {
        userDAO.createUser(user);
    }

    public List<User> findAllUsers() {
        return userDAO.getAllUsers();
    }

}
