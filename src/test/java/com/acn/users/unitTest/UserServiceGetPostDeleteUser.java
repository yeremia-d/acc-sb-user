package com.acn.users.unitTest;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import com.acn.users.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceGetPostDeleteUser {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId(1L);
        Optional<User> userOptional = Optional.of(user);

        Mockito.when(userRepository.findById(1L)).thenReturn(userOptional);
        Mockito.when(userRepository.existsById(1L)).thenReturn(true);

        User result = userService.getUserById(1L);

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);

        assertEquals(1L, result.getId().longValue());
    }

    @Test
    public void testPostUser() {
        User user = new User();
        user.setFirstname("Gary");
        user.setLastname("Lee");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User result = userService.addUser(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);

        assertEquals(user, result);
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;

        Mockito.doNothing().when(userRepository).deleteById(id);
        Mockito.when(userRepository.existsById(id)).thenReturn(true);

        userService.deleteUserById(1L);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }
}
