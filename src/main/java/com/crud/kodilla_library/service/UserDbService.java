package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.UserNotFoundException;
import com.crud.kodilla_library.domain.User;
import com.crud.kodilla_library.domain.dto.UserDto;
import com.crud.kodilla_library.mapper.UserMapper;
import com.crud.kodilla_library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapToUserDtoList(users);
    }

    public UserDto getUser(final long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userMapper.mapToUserDto(user);
    }

    @Transactional
    public UserDto createUser(final UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @Transactional
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }
}
