package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.User;
import com.crud.kodilla_library.domain.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    private User user;
    private UserDto userDto;
    private List<User> userList = new ArrayList<>();

    @BeforeEach
    void init() {
        userDto = new UserDto(1L, "firstname", "lastname", LocalDate.of(2022,8,14), new HashSet<>());
        user = new User(1L, "firstname", "lastname", LocalDate.of(2022,8,14), new HashSet<>());
        userList.add(user);
    }

    @Test
    void mapToUserTest() {
        //When
        User expected = userMapper.mapToUser(userDto);
        //Then
        assertEquals(1L, expected.getUserId());
        assertEquals("firstname", expected.getFirstname());
        assertEquals("lastname", expected.getLastname());
        assertEquals(LocalDate.of(2022,8,14), expected.getSignupDate());
    }

    @Test
    void mapToUserDtoTest() {
        //When
        UserDto expected = userMapper.mapToUserDto(user);
        //Then
        assertEquals(1L, expected.getUserId());
        assertEquals("firstname", expected.getFirstname());
        assertEquals("lastname", expected.getLastname());
        assertEquals(LocalDate.of(2022,8,14), expected.getSignupDate());
    }

    @Test
    void mapToUserDtoListTest() {
        //When
        List<UserDto> expected = userMapper.mapToUserDtoList(userList);
        //Then
        assertEquals(1, expected.size());
    }
}