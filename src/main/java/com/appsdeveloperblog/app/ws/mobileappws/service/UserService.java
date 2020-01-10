package com.appsdeveloperblog.app.ws.mobileappws.service;


import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;

import java.util.List;

public interface UserService {

    // Create user which returns UserDto
    UserDto createUser(UserDto user);

    UserDto updateUser(String id, UserDto user);

    void deleteUser(String userId);

    UserDto getUserByUserId(String id);
    UserDto getUserByEmail(String email);
    List<UserDto> getUsers(int page, int limit);
}
