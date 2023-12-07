package com.boot.api.repository;

import com.boot.api.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {
    private static List<User> userList = new ArrayList<>();
    static int userCount = 3;

    static{
        userList.add(
            User.builder()
            .id(userCount)
            .name("sobonghee")
            .joinDate(new Date())
            .password("1234")
            .build()
        );
    }
    public User save(User user){
        if(user.getId() == null) user.setId(++userCount);
        else if(user.getJoinDate() == null) user.setJoinDate(new Date());

        userList.add(user);

        return user;
    }
    public User findUser(int id){
        for(User user : userList){
            if(user.getId() == id) return user;
        }
        return null;
    }

    public List<User> userList(){
        return userList;
    }

    public void removeUser(int id){
        if(!userList.isEmpty())
            userList.removeIf(user -> user.getId() == id);
    }

    public void updateUser(int id){
        for(User user : userList){
            if(user.getId() == id)
                user.setJoinDate(new Date());
        }
    }

}
