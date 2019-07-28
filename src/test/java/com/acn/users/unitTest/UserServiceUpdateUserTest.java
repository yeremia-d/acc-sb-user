package com.acn.users.unitTest;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import com.acn.users.service.UserServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUpdateUserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private static User oldUser;

    private static String changedValue;

    @BeforeClass
    public static void initOldUser() {
        oldUser = new User();
        changedValue = "Changed";
    }

    @Before
    public void resetOldUser() {
        oldUser.setId(1L);
        oldUser.setFirstname("Scott-O");
        oldUser.setLastname("Peters-O");
        oldUser.setAddress("125 Anywhere Street., Ottawa, ON, I2D 2J2");
        oldUser.setEmail("speters@domain.com");
        oldUser.setPhone("+1 555-555-5555");
    }

    @Test
    public void updateNameOnly() {
        User updated = new User();
        updated.setId(1L);
        updated.setFirstname(changedValue);

        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
        Mockito.when(userRepository.getOne(1L)).thenReturn(oldUser);

        User result = userService.updateUser(1L, updated);

        // Only the first name should be updated
        assertEquals(changedValue, result.getFirstname());
        assertEquals(oldUser.getLastname(), result.getLastname());
        assertEquals(oldUser.getAddress(), result.getAddress());
        assertEquals(oldUser.getEmail(), result.getEmail());
        assertEquals(oldUser.getPhone(), oldUser.getPhone());
    }

}
