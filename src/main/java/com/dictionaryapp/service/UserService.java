package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);
}
