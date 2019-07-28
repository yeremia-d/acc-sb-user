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

    // Lists the first 10 users by default
    @GetMapping
    public Page<User> getUsers(
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(defaultValue = "0", required = false) int page
        ) {
        return userListingService.listUsers(limit, page);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
