package com.acn.users.service;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) throws NoSuchElementException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException("Could not find item with id " + id + ". Please verify that your parameters are set correctly.");
        }
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        if(userRepository.existsById(id)) {
            // Update the old user fields with the new fields specified in user
            User updatedUser = updateUserFields(userRepository.getOne(id), user);

            // save the user
            return userRepository.save(updatedUser);

        } else {
            throw new EntityNotFoundException("Could not update user. User with id " + id + " does not exist");
        }
    }

    private User updateUserFields(User oldUser, User newUser) {
        String  userFirstName   = newUser.getFirstname();
        String  userLastName    = newUser.getLastname();
        String  userAddress     = newUser.getAddress();
        String  userEmail       = newUser.getEmail();
        String  userPhone       = newUser.getPhone();

        if(userFirstName != null) oldUser.setFirstname(userFirstName);
        if(userLastName  != null) oldUser.setLastname(userLastName);
        if(userAddress   != null) oldUser.setAddress(userAddress);
        if(userEmail     != null) oldUser.setEmail(userEmail);
        if(userPhone     != null) oldUser.setPhone(userPhone);

        return oldUser;
    }

    @Override
    public void deleteUserById(Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Could not perform delete operation. User with id " + id + " could not be found or doesn't exist.");
        }

    }
}
