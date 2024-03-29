package com.acn.users.unitTest;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import com.acn.users.service.UserListingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class UserListingServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserListingServiceImpl userListingService;

    @Test // Test to see if the findAll method is called with the correct parameters
    public void testUserListingService() {

        Pageable requestPage = PageRequest.of(1,10);
        Page<User> pagedUserResponse = new PageImpl<>(new ArrayList<>(), requestPage, 10);

        Mockito.when(userRepository.findAll(requestPage)).thenReturn(pagedUserResponse);

        Page<User> result = userListingService.listUsers(10, 1);

        Mockito.verify(userRepository, Mockito.times(1)).findAll(requestPage);
    }
}
