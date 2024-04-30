package com.codegym.app.service.security;

import com.codegym.app.exception.EmailAlreadyExistException;
import com.codegym.app.model.dto.SignInDto;
import com.codegym.app.model.dto.SignInResponseDto;
import com.codegym.app.model.dto.SignUpDto;
import com.codegym.app.model.dto.UserDto;
import com.codegym.app.model.entity.User;
import com.codegym.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final CustomUserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public UserDto signUp(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new EmailAlreadyExistException(String.format("Email %s already exist", signUpDto.getEmail()));
        }

        User user = modelMapper.map(signUpDto, User.class);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInDto.getEmail(),
                signInDto.getPassword()
        ));

        String jwt = jwtService.generateToken(userDetailsService.loadUserByUsername(signInDto.getEmail()));
        return new SignInResponseDto(jwt);

    }
}
