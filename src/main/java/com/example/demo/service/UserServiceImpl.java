package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FilmRepository filmRepository;

    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getUsername(),
                passwordEncoder.encode(registrationDto.getPassword()),
                roleRepository.findByName("ROLE_USER"));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User findByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user, String [] roleName) {
        Set<Role> roles = new HashSet<>();
        for (String name: roleName
             ) {
            if (roleService.hasRole(name))
                roles.addAll(roleRepository.findByName(name));
        }
        user.setRoles(roles);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null)
            throw new UsernameNotFoundException("Invalid username or password.");
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPasswrod(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public void addFilmToUser(Long filmId, String username) {
        User user = userRepository.findByUsername(username);
        Set<Film> films = new HashSet<>();
        Film film = filmRepository.getOne(filmId);
        films.add(film);
        user.getFilms().addAll(films);
        userRepository.save(user);;
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).get();
        user.getRoles().clear();
        userRepository.deleteById(id);
    }

    @Override
    public void removeFilmById(String username,Long filmId) {
        User user = userRepository.findByUsername(username);
        Set<Film> films = user.getFilms();
        films.remove(filmRepository.getOne(filmId));
        user.setFilms(films);
        userRepository.save(user);
    }
}
