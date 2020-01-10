package com.appsdeveloperblog.app.ws.mobileappws.controller;

import com.appsdeveloperblog.app.ws.mobileappws.service.UserService;
import com.appsdeveloperblog.app.ws.mobileappws.shared.dto.UserDto;
import com.appsdeveloperblog.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.mobileappws.ui.model.response.OperationStatusModel;
import com.appsdeveloperblog.app.ws.mobileappws.ui.model.response.RequestOperationStatus;
import com.appsdeveloperblog.app.ws.mobileappws.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController // Betyr at denne klassen er REST, og kan da ta imot HTTP requests
@RequestMapping("users") // url til å komme til denne. URL blir: http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    // DETTE ER ET ENDEPUNKT / ENDPOINT
    @GetMapping(path="/{id}")// GET | path="/{id}" -> Makes so it is triggered when path is /users/25235235. And we map it to id variable by using @PathVariable
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();

        UserDto foundUser = userService.getUserByUserId(id);

        if (foundUser == null) throw new RuntimeException("User dont exist!");

        BeanUtils.copyProperties(foundUser, returnValue);

        return returnValue;

    }

    @GetMapping(path="/email/{email}")
    public UserRest getUserByEmail(@PathVariable String email) {
        UserRest returnValue = new UserRest();

        UserDto foundUser = userService.getUserByEmail(email);

        BeanUtils.copyProperties(foundUser, returnValue);

        return returnValue;
    }

    /**
     * @RequestBody - Så den kan lese http body request og konvertere til Java object ved hjelp av UserDetailsRequestModel
     * @return
     */
    @PostMapping // POST
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        System.out.println(userDetails);

        // Better way of mapping, this works with classes that refer to other classes! Returns a mapped object, and assign it! See old way at bottom!
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);

        // Map the created user to UserRest class and return to FE
        UserRest returnValue = modelMapper.map(createdUser, UserRest.class);

        return returnValue;
    }

    @PutMapping(path="/{id}") // PUT
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updatedUser = userService.updateUser(id, userDto);

        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @DeleteMapping(path="/{id}") // DELETE
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }

    // Nå bruker vi queryparams i requesten (?, &) da må vi hente det annerledes
    // Urlen er http://localhost:8080/users?page=1&limit=50

    /**
     *
     * @param page - Henter page parameter
     * @param limit - Henter limit parameter
     * @return
     */
    @GetMapping
    public List<UserRest> getUsers(@RequestParam(value="page", defaultValue = "0") int page, @RequestParam(value="limit", defaultValue = "25") int limit) {
        List<UserRest> returnValue = new ArrayList<>();

        List<UserDto> userDtos = userService.getUsers(page, limit);

        ModelMapper modelMapper = new ModelMapper();

        // Go through each elem and copy it to a UserRest and add it to the list we will return to FE
        for(UserDto userDto : userDtos) {
            //UserRest user = new UserRest();
            //BeanUtils.copyProperties(userDto, user);
            UserRest user = modelMapper.map(userDto, UserRest.class);
            returnValue.add(user);
        }

        return returnValue;
    }



    /// BeanUtilts metode
    /**
     * @RequestBody - Så den kan lese http body request og konvertere til Java object ved hjelp av UserDetailsRequestModel
     * @return
     */
/*    @PostMapping // POST
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        System.out.println(userDetails);

        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();

        // Kopierer data fra userDetails over til userDto, er vel som mapper I guess! BeanUtils er fra Spring
        // param: source object, target object
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);


        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }*/
}
