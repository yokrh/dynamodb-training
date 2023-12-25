package com.example.demo.app.controller;

import com.example.demo.app.controller.request.AddUserApiRequest;
import com.example.demo.app.service.UserService;
import com.example.demo.app.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HelloDynamoController {
    private final UserService userService;

    @GetMapping("/v1/user/{userId}")
    public ResponseEntity getUser(
            @PathVariable(name = "userId") Long userId
    ) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/v1/user")
    public ResponseEntity addUser(
            @RequestBody AddUserApiRequest addUserApiRequest
    ) {
        userService.addUser(addUserApiRequest);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/v1/user/{userId}")
    public ResponseEntity deleteUser(
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "userName") String userName
    ) {
        userService.deleteUser(userId, userName);
        return ResponseEntity.ok().body(null);
    }
}
