package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.controller.exceptions.UserNotFoundException;
import com.crud.kodilla_library.domain.User;
import com.crud.kodilla_library.domain.dto.UserDto;
import com.crud.kodilla_library.mapper.UserMapper;
import com.crud.kodilla_library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/library/users")
@RequiredArgsConstructor
public class UserController {

    private final DbService service;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(service.getUser(userId)));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        user.setSignupDate(LocalDate.now());
        User createdUser = service.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(createdUser));
    }
}
