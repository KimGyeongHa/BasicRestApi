package com.boot.api.controller;

import com.boot.api.exception.UserNotFoundException;
import com.boot.api.model.User;
import com.boot.api.repository.UserDaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RestController
@Tag(name = "userController",description = "사용자에 관한 정보를 얻거나 추가할 수 있는 클래스")
public class UserController {
    private final UserDaoService userDaoService;
    private final MessageSource messageSource;

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
    @Operation(summary = "사용자 정보조회 API",description = "사용자 ID를 이용해서 정보조회")
    @PostMapping("/user/{id}")
    public EntityModel<User> userInfo(@Parameter(description = "사용자 ID" ,required = true, example = "3") @PathVariable int id) {
        User user = userDaoService.findUser(id);
        if (user == null) throw new UserNotFoundException(String.format("user[%s] not found", id));

        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).getUserList());
        entityModel.add(builder.withRel("user-list"));

        return entityModel;
    }

    @GetMapping("/user/getUserList")
    public CollectionModel<List<User>> getUserList(){
        List<User> userList = userDaoService.userList();

        CollectionModel collectionModel = CollectionModel.of(userList);
        WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).getUserList());
        collectionModel.add(builder.withRel("user_list"));

        return collectionModel;
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

    @GetMapping("/international")
    public String international(@RequestHeader(name = "Accept-Language" ,required = false)Locale locale){
        return messageSource.getMessage("greeting.message",null,locale);
    }


}
