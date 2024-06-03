package edu.iset.service.signupAndLogin;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.UserEntity;
import edu.iset.dto.User;
import edu.iset.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        user = User.builder()
                .firstName("test")
                .lastName("test")
                .position("test")
                .nicNumber("test")
                .email("test@gmail.com")
                .password("test")
                .contact("test")
                .build();

        userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPosition(user.getPosition());
        userEntity.setNicNumber(user.getNicNumber());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userEntity.setContact(user.getContact());
    }

    @Test
    void testRegisterUser(){
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity registeredUser = userService.registerUser(user);

        Assertions.assertNotNull(registeredUser);
        Assertions.assertEquals(user.getFirstName(), registeredUser.getFirstName());
        Assertions.assertEquals(user.getLastName(), registeredUser.getLastName());
        Assertions.assertEquals(user.getEmail(), registeredUser.getEmail());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void testRetrieveUserByPosition(){
        when(userRepository.findAllByPosition(anyString())).thenReturn(List.of(userEntity));
        Iterable<UserEntity> users = userService.retriveUserByPosition("test");

        Assertions.assertNotNull(users);
        Assertions.assertTrue(users.iterator().hasNext());
        Assertions.assertEquals(userEntity, users.iterator().next());
        verify(userRepository, times(1)).findAllByPosition("test");
    }

    @Test
    void testAuthenticateUserSuccess() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        String result = userService.authenticateUser(user);

        Assertions.assertEquals("true", result);
        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void testAuthenticateUserFailure() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(userEntity));

        User invalidUser = User.builder()
                .email("test@gmail.com")
                .password("wrongpassword")
                .build();

        String result = userService.authenticateUser(invalidUser);

        Assertions.assertEquals("false", result);
        verify(userRepository, times(1)).findByEmail(invalidUser.getEmail());
    }

    @Test
    void testAuthenticateUserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        User invalidUser = User.builder()
                .email("invalid.email@example.com")
                .password("test")
                .build();

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.authenticateUser(invalidUser);
        });

        verify(userRepository, times(1)).findByEmail(invalidUser.getEmail());
    }


}
