package com.example.team11.Service;

import com.example.team11.Entity.User;
import com.example.team11.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);  // No password encoding
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);  // Find user by email
    }

    public boolean authenticateUser(String email, String password) {
        User user = findByEmail(email);
        if (user == null) {
            return false;  // User not found
        }
        return password.equals(user.getPassword());  // Direct comparison of passwords
    }
}
