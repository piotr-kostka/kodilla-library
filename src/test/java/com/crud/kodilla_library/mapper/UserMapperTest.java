package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.User;
import com.crud.kodilla_library.domain.dto.UserDto;
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

    @Test
    void mapToUserTest() {
        //Given
        UserDto userDto = new UserDto(1L, "firstname", "lastname", LocalDate.of(2022,8,14), new HashSet<>());
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
        //Given
        User user = new User(1L, "firstname", "lastname", LocalDate.of(2022,8,14), new HashSet<>());
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
        //Given
        User user = new User(1L, "firstname", "lastname", LocalDate.of(2022,8,14), new HashSet<>());
        List<User> list = new ArrayList<>();
        list.add(user);
        //When
        List<UserDto> expected = userMapper.mapToUserDtoList(list);
        //Then
        assertEquals(1, expected.size());
    }
}