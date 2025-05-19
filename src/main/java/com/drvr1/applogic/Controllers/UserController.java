package com.drvr1.applogic.Controllers;

import com.drvr1.applogic.Entities.User2;
import com.drvr1.applogic.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<User2> createUser(@RequestBody User2 user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}
