package com.acn.users.service;

import com.acn.users.model.User;

public interface UserService {

    // Throw exception if user not found
    public User getUserById(Long id);

    // throw malformed json exception
    public User addUser(User user);

    // Throw exception if user to be updated not found
    // Throw malformed json exception
    public User updateUser(Long id, User user);

    // Throw exception if user to be deleted not found
    public void deleteUserById(Long id);
}
