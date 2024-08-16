package com.ammycodes.querydsldynamicquery.controller;

import com.ammycodes.querydsldynamicquery.domain.UsersDTO;
import com.ammycodes.querydsldynamicquery.serviceimpl.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UsersService usersService;

    @PostMapping("/add-user")
    public ResponseEntity<UsersDTO> addUser(@RequestBody @Validated UsersDTO user)
    {
        user = usersService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
