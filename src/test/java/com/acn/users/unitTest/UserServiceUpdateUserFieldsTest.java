package com.acn.users.unitTest;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import com.acn.users.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

// Testing the update function in the UserService, where only the appropriate fields
// are updated. If the incoming user object has null fields, only the fields that
// are not null are updated.
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceUpdateUserFieldsTest {

    private static User oldUser;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeClass
    public static void initOldUser() {
        oldUser = new User();
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
    public void testNoUpdatedFields() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User updatedFields = new User();

        // Test private method via reflection
        Method methodToTest = UserServiceImpl.class.getDeclaredMethod("updateUserFields", User.class, User.class);
        methodToTest.setAccessible(true);

        User updatedUser = (User) methodToTest.invoke(userService, oldUser, updatedFields);

        Assert.assertTrue(new ReflectionEquals(updatedUser).matches(oldUser));
    }

    @Test
    public void testUpdateFields() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User updatedFields = new User();
        String updatedFirstName = "Sean-U";
        String updatedLastName = "Kwan-U";
        String updatedAddress = "Address-U";
        String updatedEmail = "Email-U";
        String updatedPhone = "Phone-U";

        updatedFields.setId(1L);
        updatedFields.setFirstname(updatedFirstName);
        updatedFields.setLastname(updatedLastName);
        updatedFields.setAddress(updatedAddress);
        updatedFields.setPhone(updatedPhone);
        updatedFields.setEmail(updatedEmail);

        User expectedUser = new User();
        expectedUser.setId(1L);
        expectedUser.setFirstname(updatedFirstName);
        expectedUser.setLastname(updatedLastName);
        expectedUser.setAddress(updatedAddress);
        expectedUser.setPhone(updatedPhone);
        expectedUser.setEmail(updatedEmail);

        // Test private method via reflection
        Method methodToTest = UserServiceImpl.class.getDeclaredMethod("updateUserFields", User.class, User.class);
        methodToTest.setAccessible(true);

        User updatedUser = (User) methodToTest.invoke(userService, oldUser, updatedFields);

        Assert.assertTrue(new ReflectionEquals(updatedUser).matches(expectedUser));
    }
}
