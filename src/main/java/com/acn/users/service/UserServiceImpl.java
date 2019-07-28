package com.acn.users.service;

import com.acn.users.model.User;
import com.acn.users.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        Optional<User> retrievedUser = userRepository.findById(id);

        if(retrievedUser.isPresent())   return retrievedUser.get();
        else                            throw new NoSuchElementException();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
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
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
