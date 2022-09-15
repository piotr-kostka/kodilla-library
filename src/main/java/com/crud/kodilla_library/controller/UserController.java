package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.domain.dto.UserDto;
import com.crud.kodilla_library.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDbService userDbService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userDbService.getAllUsers();
    }

    @GetMapping(value = "{userId}")
    public UserDto getUser(@PathVariable long userId) {
        return userDbService.getUser(userId);
    }

    @DeleteMapping(value = "{userId}")
    public void deleteUser(@PathVariable long userId) {
        userDbService.deleteUser(userId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userDbService.createUser(userDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto){
        return userDbService.createUser(userDto);
    }
}
