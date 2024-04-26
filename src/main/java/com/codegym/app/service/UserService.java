package com.codegym.app.service;

import com.codegym.app.model.dto.UpdateUserDto;
import com.codegym.app.model.dto.UserDto;
import com.codegym.app.model.entity.User;
import com.codegym.app.repository.UserRepository;
import com.codegym.app.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    public Long createUser(UserDto user) {
        return userRepository.save(modelMapper.map(user, User.class)).getId();
    }

    public List<UserDto> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

    }

    public Long update(User user) {
        return userRepository.save(user).getId();
    }

    public List<User> getAllByFirstOrLastName(String firstName, String lastName) {
        return userRepository.findAllByFirstNameOrLastName(firstName, lastName);
    }

    public List<User> searchAllByPrefixFirstName(String prefix) {
        return userRepository.findByFirstNameStartingWith(prefix);
    }

    public List<User> getAllAdultUsers() {
        return userRepository.findAllByAgeGreaterThanEqual(18);
    }

    public User getById(Long id) {
        return userRepository.getMyUserById(id);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);

    }

    public void partialUpdate(Long userId, UpdateUserDto userDto) {
        User user = userRepository.getMyUserById(userId);
        userMapper.mapUserFromUserDto(userDto, user);
        userRepository.save(user);
    }

    public void changeUserEmail(Long id, String email) {
        userRepository.updateEmail(id, email);
    }
}
