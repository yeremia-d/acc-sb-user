package com.acn.users.service;

import com.acn.users.model.User;
import org.springframework.data.domain.Page;

public interface UserListingService {
    public Page<User> listUsers(int limit, int page);
}
