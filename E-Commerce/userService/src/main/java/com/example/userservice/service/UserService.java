package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId); //UserID로 검색하겠다. DB에서 가져와서 가공할꺼기 때문에 UserDto
    Iterable<UserEntity> getUserByAll(); //반복적인(Iterable) 데이터에서. DB에서 바로 가져올꺼기 때문에.  UserEntity 가공할꺼면 UserDto로 쓸수 있따.

    };

