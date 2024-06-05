package com.user.service;

import com.user.interfaces.UserServiceInterface;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    public void createUserWithAuthId(Long authId)
    {
        User user = new User();
        user.setAuthId(authId);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserWithAuthId(Long authId)
    {
        Optional<User> user = userRepository.findByAuthId(authId);
        if(user.isPresent()) {
            userRepository.deleteByAuthId(authId);
        }
    }

}
