package com.afam.shopwave.Controllers;

import com.afam.shopwave.Models.UserModel;
import com.afam.shopwave.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getUsers() {
        return userService.getusers();
    }

    @PostMapping
    public void createUser(@RequestBody String name,
                           String username,
                           String email,
                           String password,
                           LocalDate dateofbirth) {
        userService.createUser(name, username, email, password, dateofbirth);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteStudent(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }


    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) LocalDate dateofbirth

    ) {
        userService.updateUser(userId, email, name, username, password, dateofbirth);
    }

    @PostMapping(path = "/login")
    public void loginUser(
            @RequestBody String email,
            String password
    ) {
        userService.loginUser(email, password);
    }


}
