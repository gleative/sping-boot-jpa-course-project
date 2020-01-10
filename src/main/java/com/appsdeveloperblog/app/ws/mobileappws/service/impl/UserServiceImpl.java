package com.appsdeveloperblog.app.ws.mobileappws.service.impl;

import com.appsdeveloperblog.app.ws.mobileappws.UserRepository;
import com.appsdeveloperblog.app.ws.mobileappws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.mobileappws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.mobileappws.service.UserService;
import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.AddressDto;
import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Here we write our business logic (logic to save data to database)
@Service
public class UserServiceImpl implements UserService {

    @Autowired // Now i can use it
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        // Checks if the user already exist by checking email. We have email as unique as well!
        if (userRepository.findUserByEmail(user.getEmail()) != null) throw new RuntimeException("Not accepted, email already exist!");

        //UserEntity userEntity = new UserEntity();

        // Use BeanUtils to copy the properties from user in param and copy to the userEntity
        // NB! UserDto og UserEntity MÅ MATCHE med fields!
        //BeanUtils.copyProperties(user, userEntity);

        // Loop through addresses the user has, and assign them to the user, AND create public id for each address the user has
        for (AddressDto addressDto: user.getAddresses()) {
            addressDto.setUserDetails(user);
            addressDto.setAddressId(UUID.randomUUID().toString());
        }

        ModelMapper modelMapper = new ModelMapper();

        // Copy properties to userEntity
        UserEntity userEntity  = modelMapper.map(user, UserEntity.class);


        userEntity.setEncryptedPassword("test");
        userEntity.setUserId(UUID.randomUUID().toString());



        // We save the entity to the database using the already made function from CrudRepository
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findUserByUserId(userId);

        if (userEntity == null) throw new RuntimeException("User Id does not exist!");

        // Update the values, here you can choose what to update or not!
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updatedUser = userRepository.save(userEntity);

        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findUserByUserId(userId);

        if (userEntity == null) throw new RuntimeException("User dont exist!");

        userRepository.delete(userEntity);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
       // UserEntity returnValue = userRepository.findById(Long.parseLong(id));

        UserDto returnValue = new UserDto();

        UserEntity userEntity = userRepository.findUserByUserId(userId);

        if (userEntity == null) throw new RuntimeException("UserId not found.");

        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserDto returnValue = new UserDto();

        UserEntity userEntity = userRepository.findUserByEmail(email);

        if (userEntity == null) throw new RuntimeException("User email not found.");

        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        // We use findAll(Pageable pageable)
        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        // Convert it to Page to List
        List<UserEntity> users = usersPage.getContent();

        ModelMapper modelMapper = new ModelMapper();

        for(UserEntity userEntity: users) {
            //UserDto userDto = new UserDto();
            //BeanUtils.copyProperties(userEntity, userDto);

            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            returnValue.add(userDto);
        }

        return returnValue;
    }


}
