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
            User    userToUpdate    = userRepository.getOne(id);
            String  userFirstName   = user.getFirstname();
            String  userLastName    = user.getLastname();
            String  userAddress     = user.getAddress();
            String  userEmail       = user.getEmail();
            String  userPhone       = user.getPhone();

            // update the required fields
            if(userFirstName != null) userToUpdate.setFirstname(userFirstName);
            if(userLastName  != null) userToUpdate.setLastname(userLastName);
            if(userAddress   != null) userToUpdate.setAddress(userAddress);
            if(userEmail     != null) userToUpdate.setEmail(userEmail);
            if(userPhone     != null) userToUpdate.setPhone(userPhone);

            // save the user
            userRepository.save(userToUpdate);
            return userToUpdate;

        } else {
            throw new EntityNotFoundException("Could not update user. User with id " + id + " does not exist");
        }
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
