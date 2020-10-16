package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.User;
import com.example.demo.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    List<User> getAllUsers();
    User findByName(String name);
    User findById(Long id);
    void updateUser(User user, String[] roles);
    void addFilmToUser(Long filmId, String username);
    void deleteUserById(Long id);
    void removeFilmById(String username,Long filmId);
}
