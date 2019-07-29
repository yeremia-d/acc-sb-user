package com.acn.users.controller;

import com.acn.users.model.User;
import com.acn.users.service.UserListingService;
import com.acn.users.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserListingService userListingService;
    private final UserService userService;

    UserController(UserListingService userListingService, UserService userService) {
        this.userListingService = userListingService;
        this.userService = userService;
    }

    // Get endpoint that gets a list of users
    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "10", required = false) int limit, @RequestParam(defaultValue = "0", required = false) int page) {
        return userListingService.listUsers(limit, page);
    }

    // Get endpoint that gets a single user with id (in path variable)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Posts endpoint for creating new users
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Put endpoint for updating existing user with id specified in path variable
    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete endpoint that deletes user with id as specified in path variable.
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
