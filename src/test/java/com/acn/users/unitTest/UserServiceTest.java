package com.acn.users.unitTest;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import com.acn.users.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

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

        Assert.assertTrue(new ReflectionEquals(result).matches(user));
    }

    @Test
    public void testUpdateUser() {
        User updatedUser = new User();
        Long id = 1L;
        updatedUser.setId(id);
        updatedUser.setFirstname("ChangedFirstName");

        Mockito.when(userRepository.getOne(id)).thenReturn(updatedUser);
        Mockito.when(userRepository.existsById(id)).thenReturn(true);
        Mockito.when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUser(id, updatedUser);

        Mockito.verify(userRepository, Mockito.times(1)).save(updatedUser);

        Assert.assertTrue(new ReflectionEquals(result).matches(updatedUser));
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
