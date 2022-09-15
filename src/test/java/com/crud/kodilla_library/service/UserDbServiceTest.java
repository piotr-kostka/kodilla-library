package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.UserNotFoundException;
import com.crud.kodilla_library.domain.User;
import com.crud.kodilla_library.domain.dto.UserDto;
import com.crud.kodilla_library.mapper.UserMapper;
import com.crud.kodilla_library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDbServiceTest {

    @InjectMocks
    private UserDbService userDbService;

    @Mock
    private UserMapper userMapperMock;

    @Mock
    private UserRepository userRepositoryMock;

    private User user;
    private UserDto userDto;
    private List<User> userList = new ArrayList<>();
    private List<UserDto> userDtoList = new ArrayList<>();

    @BeforeEach
    void init() {
        user = new User(1L, "firstname", "lastname", LocalDate.of(2022,8,15),new HashSet<>());
        userDto = new UserDto(1L, "firstname", "lastname", LocalDate.of(2022,8,15),new HashSet<>());
        userList.add(user);
        userDtoList.add(userDto);
    }

    @Test
    void getAllUsersTest() {
        //Given
        when(userMapperMock.mapToUserDtoList(userList)).thenReturn(userDtoList);
        when(userRepositoryMock.findAll()).thenReturn(userList);

        //When
        List<UserDto> expectedList = userDbService.getAllUsers();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals("firstname", expectedList.get(0).getFirstname());
    }

    @Test
    void getUserTest() throws UserNotFoundException {
        //Given
        when(userMapperMock.mapToUserDto(user)).thenReturn(userDto);
        when(userRepositoryMock.findById(userDto.getUserId())).thenReturn(Optional.of(user));

        //When
        UserDto expectedUserDto = userDbService.getUser(1);

        //Then
        assertEquals("firstname", expectedUserDto.getFirstname());
        assertEquals("lastname", expectedUserDto.getLastname());
    }

    @Test
    void createUserTest() {
        //Given
        when(userMapperMock.mapToUser(userDto)).thenReturn(user);
        User savedUser = userMapperMock.mapToUser(userDto);
        when(userRepositoryMock.save(user)).thenReturn(savedUser);
        when(userMapperMock.mapToUserDto(savedUser)).thenReturn(userDto);

        //When
        UserDto expectedUserDto = userDbService.createUser(userDto);
        //Then
        assertEquals(1, expectedUserDto.getUserId());
        assertEquals("firstname", expectedUserDto.getFirstname());
    }

    @Test
    void deleteUserTest() {
        //Given
        //When
        userDbService.deleteUser(1L);
        //Then
        verify(userRepositoryMock, times(1)).deleteById(1L);
    }
}