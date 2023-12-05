package com.boot.api.controller;

import com.boot.api.exception.UserNotFoundException;
import com.boot.api.model.User;
import com.boot.api.repository.UserDaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserDaoService userDaoService;

    @PostMapping("/user")
    public ResponseEntity<User> joinUser(@Valid @RequestBody User user){
        User joinUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(joinUser.getId()) // path값에 바인드
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/user/{id}")
    public User userInfo(@PathVariable int id){
        User user = userDaoService.findUser(id);
        if(user == null) throw new UserNotFoundException(String.format("user[%s] not found",id));

        return user;
    }

    @GetMapping("/user/getUserList")
    public List<User> getUserList(){
        return userDaoService.userList();
    }

    @PatchMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id){
        userDaoService.updateUser(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id){
        userDaoService.removeUser(id);
        return ResponseEntity.noContent().build();
    }


}
