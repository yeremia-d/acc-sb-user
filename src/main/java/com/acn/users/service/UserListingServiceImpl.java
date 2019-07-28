package com.acn.users.service;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserListingServiceImpl implements UserListingService {
    private final UserRepository userRepository;

    UserListingServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> listUsers(int limit, int page) {
        Pageable query = PageRequest.of(page, limit);

        return userRepository.findAll(query);
    }
}
