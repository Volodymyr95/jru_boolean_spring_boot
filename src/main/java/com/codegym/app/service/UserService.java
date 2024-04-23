package com.codegym.app.service;

import com.codegym.app.model.dto.UserDto;
import com.codegym.app.model.entity.User;
import com.codegym.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Long createUser(UserDto user) {
        return userRepository.save(modelMapper.map(user, User.class)).getId();
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
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
}
