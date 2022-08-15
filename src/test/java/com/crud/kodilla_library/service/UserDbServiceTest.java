package com.crud.kodilla_library.service;

import com.crud.kodilla_library.domain.User;
import com.crud.kodilla_library.domain.dto.UserDto;
import com.crud.kodilla_library.mapper.UserMapper;
import com.crud.kodilla_library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDbServiceTest {

    @InjectMocks
    private UserDbService userDbService;

    @Mock
    private UserMapper userMapperMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Test
    void getAllUsers() {
        //Given
        UserDto userDto = new UserDto(1L, "firstname", "lastname", LocalDate.of(2022,8,15),new HashSet<>());
        User user = new User(1L, "firstname", "lastname", LocalDate.of(2022,8,15),new HashSet<>());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);

        when(userMapperMock.mapToUser(userDto)).thenReturn(user);
        when(userMapperMock.mapToUserDto(user)).thenReturn(userDto);
        when(userMapperMock.mapToUserDtoList(userList)).thenReturn(userDtoList);

        when(userRepositoryMock.findAll()).thenReturn(userList);
        when(userRepositoryMock.findById(user.getUserId())).thenReturn(...);
        when(userRepositoryMock.save(user)).thenReturn(...);
        when(userRepositoryMock.deleteById(user.getUserId())).thenReturn(...);

        //When
        List<UserDto> expectedList = userDbService.getAllUsers();
        //Then
        assertEquals(1, expectedList.size());
        assertEquals("firstname", expectedList.get(0).getFirstname());
    }

    @Test
    void getUser() {
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }
}