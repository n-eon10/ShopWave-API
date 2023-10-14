package com.afam.shopwave.Services;

import com.afam.shopwave.Models.UserModel;
import com.afam.shopwave.Repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getusers() {
        return userRepository.findAll();

    }

    public void createUser (String name, String username, String email, String password, LocalDate dateofbirth) {

        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("Username is already in use");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("Email is already in use");
        }

        UserModel newUser = new UserModel(name, username, password, email, dateofbirth);

        System.out.println(newUser);
        userRepository.save(newUser);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with id" + userId + "does not exist");
        }

        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String email, String name, String username, String password, LocalDate dateofbirth) {

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " does not exist"));


        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }

        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }

        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }

        if (password != null && !password.isEmpty()) {

            user.setPassword(password);
        }

        if (dateofbirth != null) {
            user.setDateofbirth(dateofbirth);
        }

        userRepository.save(user);
    }
}
