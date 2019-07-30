package com.acn.users.unitTest;

import com.acn.users.model.Address;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Testing the update function in the UserService, where only the appropriate fields
// are updated. If the incoming user object has null fields, only the fields that
// are not null are updated.
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
        Address address = new Address();
        address.setMutableFields("125 Anywhere Street", "Ottawa", "ON", "I2D 2J2");

        oldUser.setId(1L);
        oldUser.setMutableFields("Scott-O", "Peters-O", address, "+1 555-555-5555", "speters@domain.com");
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

        String  updatedFirstName = "Sean-U";
        String  updatedLastName  = "Kwan-U";
        Address updatedAddress   = new Address();
        String  updatedEmail     = "Email-U";
        String  updatedPhone     = "Phone-U";

        updatedAddress.setMutableFields("Changed-Street", "Changed-City", "Changed-Province", "Changed-PostalCode");

        User updatedFields = new User();
        updatedFields.setId(1L);
        updatedFields.setMutableFields(updatedFirstName, updatedLastName, updatedAddress, updatedPhone, updatedEmail);

        User expectedUser = new User();
        expectedUser.setId(1L);
        expectedUser.setMutableFields(updatedFirstName, updatedLastName, updatedAddress, updatedPhone, updatedEmail);

        // Test private method via reflection
        Method methodToTest = UserServiceImpl.class.getDeclaredMethod("updateUserFields", User.class, User.class);
        methodToTest.setAccessible(true);

        User updatedUser = (User) methodToTest.invoke(userService, oldUser, updatedFields);

        Assert.assertTrue(new ReflectionEquals(updatedUser).matches(expectedUser));
    }
}
