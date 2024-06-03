package edu.iset.controller;

import edu.iset.dao.UserEntity;
import edu.iset.dto.User;
import edu.iset.service.signupAndLogin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //authenticate user
    @PostMapping("/authenticateUser")
    public  String authenticateUser(@RequestBody User user){
        return userService.authenticateUser(user);
    }

    //save new user
    @PostMapping
    public void userRegistration(@RequestBody User user){
        userService.registerUser(user);
    }

    //get users by position
    @GetMapping("retrive-user/{position}")
    public Iterable<UserEntity> retriveStudentByPosition(@PathVariable String position){
        return userService.retriveUserByPosition(position);
    }
}
