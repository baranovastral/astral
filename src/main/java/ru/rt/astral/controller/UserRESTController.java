package ru.rt.astral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.rt.astral.model.User;
import ru.rt.astral.service.UserService;

@RestController
@RequestMapping("/user")
public class UserRESTController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(
            value = "/register",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody User user){
        switch (userService.createUser(user)) {
            case UserService.OK:{
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            case UserService.USER_EXISTS:{
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    
    
}
