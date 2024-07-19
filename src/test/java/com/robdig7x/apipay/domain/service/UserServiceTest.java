package com.robdig7x.apipay.domain.service;

import com.robdig7x.apipay.domain.model.entity.Users;
import com.robdig7x.apipay.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_shouldReturnSavedUser() {
        Users users = new Users();
        users.setUsername("testUser");
        users.setPassword("password123");

        Users savedUser = new Users();
        savedUser.setId(1L);
        savedUser.setUsername("testUser");
        savedUser.setPassword("encodedPassword");

        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepository.save(any(Users.class))).thenReturn(savedUser);

        Users result = userService.createUser(users);

        assertEquals(savedUser.getId(), result.getId());
        assertEquals(savedUser.getUsername(), result.getUsername());
        assertEquals(savedUser.getPassword(), result.getPassword());

        verify(passwordEncoder, times(1)).encode("password123");
        verify(userRepository, times(1)).save(users);
    }
}