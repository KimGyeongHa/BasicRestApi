package com.boot.api.controller;

import com.boot.api.exception.UserNotFoundException;
import com.boot.api.model.AdminUser;
import com.boot.api.model.User;
import com.boot.api.repository.UserDaoService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@RestController
public class AdminUserController {

    private final UserDaoService userDaoService;
    private final MessageSource messageSource;

    @PostMapping("/user/{id}")
    public MappingJacksonValue adminUserInfo(@PathVariable int id){
        User user = userDaoService.findUser(id);
        AdminUser adminUser = new AdminUser();
        if(user == null) {
            throw new UserNotFoundException(String.format("user[%s] not found",id));
        }else BeanUtils.copyProperties(user,adminUser); // 다른 객체의 property 사용을 위한 메서드

        // 필터설정
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("name","joinDate"); // 보여주고 싶은 필드등록
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userInfo",filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(adminUser);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/users")
    public MappingJacksonValue getUsers(){
        List<User> userList = userDaoService.userList();

        List<AdminUser> adminUserList = new LinkedList<>();
        AdminUser adminUser = null;
        for(User user : userList){
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user,adminUser);
            adminUserList.add(adminUser);
        }

        // 필터설정
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("name","joinDate"); // 보여주고 싶은 필드등록
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userInfo",filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(adminUserList);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

}
